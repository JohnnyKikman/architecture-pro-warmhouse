package ru.smarthome.temperature_api.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TemperatureResponse(
        float value,
        String unit,
        LocalDateTime timestamp,
        String location,
        String status,
        @JsonProperty("sensor_id") String sensorId,
        @JsonProperty("sensor_type") String sensorType,
        String description
) {
}
