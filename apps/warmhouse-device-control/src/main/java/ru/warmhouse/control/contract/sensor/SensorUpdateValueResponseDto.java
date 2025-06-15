package ru.warmhouse.control.contract.sensor;

import lombok.Data;

@Data
public class SensorUpdateValueResponseDto {
    private String message;
    private String error;
}
