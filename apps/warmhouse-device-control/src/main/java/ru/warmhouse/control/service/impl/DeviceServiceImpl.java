package ru.warmhouse.control.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.warmhouse.control.client.SensorClient;
import ru.warmhouse.control.contract.api.DeviceResponseDto;
import ru.warmhouse.control.contract.api.DeviceStateDto;
import ru.warmhouse.control.contract.sensor.SensorDto;
import ru.warmhouse.control.contract.sensor.SensorUpdateValueRequestDto;
import ru.warmhouse.control.contract.sensor.SensorUpdateValueResponseDto;
import ru.warmhouse.control.entity.Device;
import ru.warmhouse.control.entity.DeviceType;
import ru.warmhouse.control.repository.DeviceRepository;
import ru.warmhouse.control.repository.DeviceTypeRepository;
import ru.warmhouse.control.service.DeviceService;
import ru.warmhouse.control.service.mapper.DeviceEntityMapper;
import ru.warmhouse.control.service.mapper.SensorDeviceMapper;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final SensorClient sensorClient;
    private final DeviceRepository deviceRepository;
    private final DeviceEntityMapper deviceEntityMapper;
    private final SensorDeviceMapper sensorDeviceMapper;
    private final DeviceTypeRepository deviceTypeRepository;

    @Override
    @Transactional
    public DeviceResponseDto getDevice(Long id) {
        Optional<Device> existingDevice = deviceRepository.findById(id);
        if (existingDevice.isPresent()) {
            return deviceEntityMapper.toDto(existingDevice.get());
        }

        SensorDto sensorDto = sensorClient.getSensorById(id);

        DeviceType deviceType = deviceTypeRepository.findByUnitType(DeviceType.UnitType.CELSIUS)
                .orElseGet(() -> {
                    DeviceType celsiusDeviceType = new DeviceType();
                    celsiusDeviceType.setUnitType(DeviceType.UnitType.CELSIUS);
                    celsiusDeviceType.setName("temperature");
                    return deviceTypeRepository.save(celsiusDeviceType);
                });

        Device newDevice = new Device();
        newDevice.setId(sensorDto.getId());
        newDevice.setName(sensorDto.getName());
        newDevice.setConnected(true);
        newDevice.setEnabled("ACTIVE".equalsIgnoreCase(sensorDto.getStatus()));
        newDevice.setTargetValue(sensorDto.getValue().toPlainString());
        newDevice.setActualValue(sensorDto.getValue().toPlainString());
        newDevice.setDateTimeUpdated(sensorDto.getLastUpdated() != null
                ? sensorDto.getLastUpdated().atZone(ZoneId.systemDefault()) : ZonedDateTime.now());
        newDevice.setDeviceType(deviceType);

        return deviceEntityMapper.toDto(deviceRepository.save(newDevice));
    }

    /**
     * Redirects to monolithic application's GET /api/v1/sensors/:id
     */
    @Override
    @Transactional
    public DeviceResponseDto refreshDevice(Long id) {
        SensorDto sensorDto = sensorClient.getSensorById(id);

        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Device not found: " + id));
        device.setEnabled("ACTIVE".equalsIgnoreCase(sensorDto.getStatus()));
        device.setActualValue(sensorDto.getValue().toPlainString());
        device.setDateTimeUpdated(sensorDto.getLastUpdated() != null
                ? sensorDto.getLastUpdated().atZone(ZoneId.systemDefault()) : ZonedDateTime.now());
        deviceRepository.save(device);

        return sensorDeviceMapper.mapToDeviceResponse(sensorDto);
    }

    /**
     * Redirects to monolithic application's PATCH /api/v1/sensors/:id/value
     */
    @Override
    @Transactional
    public DeviceResponseDto sendCommand(DeviceStateDto stateDto) {
        Long sensorId = stateDto.getId();
        Device device = deviceRepository.findById(sensorId)
                .orElseThrow(() -> new IllegalArgumentException("Device not found: " + sensorId));

        SensorUpdateValueRequestDto updateDto = new SensorUpdateValueRequestDto();
        updateDto.setStatus(stateDto.getEnabled() != null && stateDto.getEnabled() ? "ACTIVE" : "INACTIVE");

        if (stateDto.getTargetValue() != null) {
            updateDto.setValue(new BigDecimal(stateDto.getTargetValue()));
        }

        SensorUpdateValueResponseDto updated = sensorClient.updateSensorValue(sensorId, updateDto);
        if (updated.getError() != null) {
            throw new RuntimeException("Error when updating sensor value with id: " + sensorId);
        }
        device.setTargetValue(stateDto.getTargetValue());
        device.setConnected(stateDto.getEnabled());
        deviceRepository.save(device);
        return deviceEntityMapper.toDto(device);
    }

}
