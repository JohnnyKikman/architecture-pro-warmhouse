package ru.smarthome.temperature_api.controller;

import org.springframework.web.bind.annotation.*;
import ru.smarthome.temperature_api.contract.TemperatureResponse;

import java.util.Random;

@RestController
@RequestMapping("temperature")
public class TemperatureController {

    private final Random random = new Random();

    /**
     * Получение температуры по расположению датчика
     *
     * @param location расположение датчика
     * @return {@link TemperatureResponse} с данными о датчике и его температуре
     */
    @GetMapping
    public TemperatureResponse getTemperatureByLocation(@RequestParam("location") String location) {
        return TemperatureResponse.builder()
                .value(random.nextFloat(-40, 40))
                .location(location)
                .build();
    }

    /**
     * Получение температуры по ID датчика
     *
     * @param sensorId ID датчика
     * @return {@link TemperatureResponse} с данными о датчике и его температуре
     */
    @GetMapping("/{sensorId}")
    public TemperatureResponse getTemperatureByLocation(@PathVariable("sensorId") Integer sensorId) {
        return TemperatureResponse.builder()
                .value(random.nextFloat(-40, 40))
                .sensorId(String.valueOf(sensorId))
                .build();
    }
}
