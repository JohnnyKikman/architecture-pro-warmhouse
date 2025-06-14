package ru.smarthome.temperature_api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.smarthome.temperature_api.TemperatureApiApplication;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = TemperatureApiApplication.class)
public class WebMvcConfiguration {
}
