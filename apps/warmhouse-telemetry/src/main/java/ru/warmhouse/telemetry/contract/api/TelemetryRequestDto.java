package ru.warmhouse.telemetry.contract.api;

import lombok.Data;

@Data
public class TelemetryRequestDto {
    private Integer page;
    private Integer size;
    private DateTimeFilter dateTime;
}