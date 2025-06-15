package ru.warmhouse.telemetry.contract.api;

import lombok.Data;

import java.util.List;

@Data
public class TelemetryPageResponseDto {
    private Long total;
    private List<TelemetryDto> data;
}