package ru.warmhouse.telemetry.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.warmhouse.telemetry.entity.Telemetry;

import java.time.ZonedDateTime;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {
    Page<Telemetry> findByDeviceIdAndDateTimeBetween(Long deviceId, ZonedDateTime from, ZonedDateTime to, Pageable pageable);
}
