package ru.warmhouse.telemetry.service;

import ru.warmhouse.telemetry.contract.api.TelemetryDto;
import ru.warmhouse.telemetry.contract.api.TelemetryPageResponseDto;
import ru.warmhouse.telemetry.contract.api.TelemetryRequestDto;

public interface TelemetryService {

    TelemetryDto getLatestTelemetry(Long deviceId);

    TelemetryPageResponseDto getTelemetryPage(Long deviceId, TelemetryRequestDto request);

}