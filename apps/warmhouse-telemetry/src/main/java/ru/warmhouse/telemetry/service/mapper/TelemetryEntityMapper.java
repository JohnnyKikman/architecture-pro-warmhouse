package ru.warmhouse.telemetry.service.mapper;

import org.springframework.stereotype.Service;
import ru.warmhouse.telemetry.contract.api.TelemetryDto;
import ru.warmhouse.telemetry.entity.Telemetry;

@Service
public class TelemetryEntityMapper {

    public TelemetryDto toDto(Telemetry telemetry) {
        TelemetryDto dto = new TelemetryDto();
        dto.setDateTime(telemetry.getDateTime());
        dto.setEnabled(telemetry.getEnabled());
        dto.setValue(telemetry.getValue());
        dto.setUnitType(telemetry.getUnitType());
        return dto;
    }
}
