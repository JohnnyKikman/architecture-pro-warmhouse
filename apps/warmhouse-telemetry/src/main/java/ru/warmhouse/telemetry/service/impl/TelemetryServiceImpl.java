package ru.warmhouse.telemetry.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.warmhouse.telemetry.client.SensorClient;
import ru.warmhouse.telemetry.contract.sensor.SensorDto;
import ru.warmhouse.telemetry.repository.TelemetryRepository;
import ru.warmhouse.telemetry.contract.api.TelemetryDto;
import ru.warmhouse.telemetry.contract.api.TelemetryPageResponseDto;
import ru.warmhouse.telemetry.contract.api.TelemetryRequestDto;
import ru.warmhouse.telemetry.entity.Telemetry;
import ru.warmhouse.telemetry.service.TelemetryService;
import ru.warmhouse.telemetry.service.mapper.TelemetryEntityMapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelemetryServiceImpl implements TelemetryService {

    private final SensorClient sensorClient;
    private final TelemetryRepository telemetryRepository;
    private final TelemetryEntityMapper telemetryEntityMapper;

    /**
     * Redirects to monolithic application's GET /api/v1/sensors/:id, which implicitly updates temperature from sensor
     */
    @Override
    @Transactional
    public TelemetryDto getLatestTelemetry(Long deviceId) {
        SensorDto sensorDto = sensorClient.getSensorById(deviceId);

        Telemetry telemetry = new Telemetry();
        telemetry.setDeviceId(deviceId);
        telemetry.setDateTime(sensorDto.getLastUpdated() != null
                ? sensorDto.getLastUpdated().atZone(ZoneId.systemDefault()) : ZonedDateTime.now());
        telemetry.setEnabled("ACTIVE".equalsIgnoreCase(sensorDto.getStatus()));
        telemetry.setValue(sensorDto.getValue().toPlainString());
        telemetry.setUnitType(sensorDto.getUnit());

        return telemetryEntityMapper.toDto(telemetryRepository.save(telemetry));
    }

    @Override
    public TelemetryPageResponseDto getTelemetryPage(Long deviceId, TelemetryRequestDto request) {
        ZonedDateTime from = request.getDateTime().getFrom();
        ZonedDateTime to = request.getDateTime().getTo();

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());
        Page<Telemetry> page = telemetryRepository.findByDeviceIdAndDateTimeBetween(deviceId, from, to, pageRequest);

        List<TelemetryDto> dtos = page.getContent().stream().map(telemetryEntityMapper::toDto).collect(Collectors.toList());

        TelemetryPageResponseDto response = new TelemetryPageResponseDto();
        response.setTotal(page.getTotalElements());
        response.setData(dtos);
        return response;
    }
}