package ru.warmhouse.control.contract.sensor;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SensorUpdateValueRequestDto {
    private BigDecimal value;
    private String status;
}
