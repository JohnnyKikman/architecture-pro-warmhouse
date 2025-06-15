package ru.warmhouse.control.service.mapper;

import org.springframework.stereotype.Component;
import ru.warmhouse.control.contract.api.DeviceResponseDto;
import ru.warmhouse.control.contract.api.DeviceTypeDto;
import ru.warmhouse.control.entity.Device;

@Component
public class DeviceEntityMapper {

    public DeviceResponseDto toDto(Device entity) {
        DeviceResponseDto dto = new DeviceResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setConnected(entity.getConnected());
        dto.setEnabled(entity.getEnabled());
        dto.setTargetValue(entity.getTargetValue());
        dto.setActualValue(entity.getActualValue());
        dto.setDateTimeUpdated(entity.getDateTimeUpdated());

        if (entity.getDeviceType() != null) {
            DeviceTypeDto type = new DeviceTypeDto();
            type.setName(entity.getDeviceType().getName());
            type.setUnitType(entity.getDeviceType().getUnitType().name());
            dto.setDeviceType(type);
        }

        return dto;
    }
}
