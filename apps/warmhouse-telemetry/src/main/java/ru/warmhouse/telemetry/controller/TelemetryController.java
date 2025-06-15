package ru.warmhouse.telemetry.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.telemetry.contract.api.TelemetryDto;
import ru.warmhouse.telemetry.contract.api.TelemetryPageResponseDto;
import ru.warmhouse.telemetry.contract.api.TelemetryRequestDto;
import ru.warmhouse.telemetry.service.TelemetryService;

@RestController
@RequestMapping("/api/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;

    @GetMapping("/{deviceId}")
    public ResponseEntity<TelemetryDto> getLatestTelemetry(@PathVariable("deviceId") Long deviceId) {
        return ResponseEntity.ok(telemetryService.getLatestTelemetry(deviceId));
    }

    @PostMapping("/{deviceId}/getPage")
    public ResponseEntity<TelemetryPageResponseDto> getTelemetryPage(
            @PathVariable("deviceId") Long deviceId,
            @RequestBody TelemetryRequestDto request) {
        return ResponseEntity.ok(telemetryService.getTelemetryPage(deviceId, request));
    }
}