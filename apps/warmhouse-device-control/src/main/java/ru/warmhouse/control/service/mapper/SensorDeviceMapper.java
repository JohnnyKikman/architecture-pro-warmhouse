package ru.warmhouse.control.service.mapper;

import org.springframework.stereotype.Component;
import ru.warmhouse.control.contract.api.DeviceResponseDto;
import ru.warmhouse.control.contract.api.DeviceTypeDto;
import ru.warmhouse.control.contract.sensor.SensorDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class SensorDeviceMapper {

    public DeviceResponseDto mapToDeviceResponse(SensorDto sensor) {
        DeviceResponseDto dto = new DeviceResponseDto();
        dto.setId(sensor.getId());
        dto.setName(sensor.getName());
        dto.setConnected("ACTIVE".equalsIgnoreCase(sensor.getStatus()));
        dto.setEnabled("ACTIVE".equalsIgnoreCase(sensor.getStatus()));
        dto.setTargetValue(sensor.getValue() != null ? sensor.getValue().toPlainString() : null);
        dto.setActualValue(sensor.getValue() != null ? sensor.getValue().toPlainString() : null);
        dto.setDateTimeUpdated(sensor.getLastUpdated() != null
                ? sensor.getLastUpdated().atZone(ZoneId.systemDefault()) : ZonedDateTime.now());

        DeviceTypeDto deviceType = new DeviceTypeDto();
        deviceType.setName(sensor.getType() != null ? sensor.getType() : "Unknown");
        deviceType.setUnitType(sensor.getUnit());
        dto.setDeviceType(deviceType);

        return dto;
    }
}
