package ru.warmhouse.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.warmhouse.control.entity.DeviceType;

import java.util.Optional;

public interface DeviceTypeRepository extends JpaRepository<DeviceType, Long> {

    Optional<DeviceType> findByUnitType(DeviceType.UnitType unitType);

}
