package ru.warmhouse.control.service;

import ru.warmhouse.control.contract.api.DeviceResponseDto;
import ru.warmhouse.control.contract.api.DeviceStateDto;

public interface DeviceService {

    DeviceResponseDto getDevice(Long id);

    DeviceResponseDto refreshDevice(Long id);

    DeviceResponseDto sendCommand(DeviceStateDto stateDto);

}
