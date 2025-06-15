package ru.warmhouse.control.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.warmhouse.control.contract.sensor.SensorDto;
import ru.warmhouse.control.contract.sensor.SensorUpdateValueRequestDto;
import ru.warmhouse.control.contract.sensor.SensorUpdateValueResponseDto;

@Component
@RequiredArgsConstructor
public class SensorClient {

    private final WebClient sensorWebClient;

    public SensorDto getSensorById(Long id) {
        return sensorWebClient
                .get()
                .uri("/sensors/{id}", id)
                .retrieve()
                .bodyToMono(SensorDto.class)
                .block();
    }

    public SensorUpdateValueResponseDto updateSensorValue(Long id, SensorUpdateValueRequestDto updateDto) {
        return sensorWebClient
                .patch()
                .uri("/sensors/{id}/value", id)
                .bodyValue(updateDto)
                .retrieve()
                .bodyToMono(SensorUpdateValueResponseDto.class)
                .block();
    }
}
