package ru.warmhouse.control.contract.api;

import lombok.Data;

@Data
public class DeviceStateDto {
    private Long id;
    private Boolean enabled;
    private String targetValue;
}
