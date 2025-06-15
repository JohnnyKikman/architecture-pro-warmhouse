package ru.warmhouse.telemetry.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.warmhouse.telemetry.contract.sensor.SensorDto;

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

}
