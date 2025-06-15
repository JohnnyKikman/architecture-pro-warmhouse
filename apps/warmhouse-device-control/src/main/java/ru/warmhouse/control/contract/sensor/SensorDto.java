package ru.warmhouse.control.contract.sensor;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SensorDto {
    private Long id;
    private String name;
    private String type;
    private String location;
    private BigDecimal value;
    private String unit;
    private String status;
    private LocalDateTime lastUpdated;
    private LocalDateTime createdAt;
}
