package ru.warmhouse.control.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.warmhouse.control.contract.api.DeviceResponseDto;
import ru.warmhouse.control.contract.api.DeviceStateDto;
import ru.warmhouse.control.service.DeviceService;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDto> getDevice(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.getDevice(id));
    }

    @GetMapping("/{id}/actual")
    public ResponseEntity<DeviceResponseDto> refreshDeviceData(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.refreshDevice(id));
    }

    @PutMapping
    public ResponseEntity<DeviceResponseDto> sendCommandToDevice(@RequestBody DeviceStateDto request) {
        return ResponseEntity.ok(deviceService.sendCommand(request));
    }
}