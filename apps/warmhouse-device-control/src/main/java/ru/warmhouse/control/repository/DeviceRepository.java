package ru.warmhouse.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.warmhouse.control.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
