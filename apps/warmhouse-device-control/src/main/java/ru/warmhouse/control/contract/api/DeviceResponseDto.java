package ru.warmhouse.control.contract.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DeviceResponseDto {

    private Long id;
    private String name;
    private Boolean connected;
    private Boolean enabled;
    private String targetValue;
    private String actualValue;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime dateTimeUpdated;
    private DeviceTypeDto deviceType;

}
