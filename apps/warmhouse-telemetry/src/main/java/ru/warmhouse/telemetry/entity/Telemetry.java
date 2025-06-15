package ru.warmhouse.telemetry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Entity
@Table(name = "telemetry_data")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Telemetry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private Long deviceId;

    @Column(name = "date_time", nullable = false)
    private ZonedDateTime dateTime;

    @Column(name = "active", nullable = false)
    private Boolean enabled;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "unit_type", nullable = false)
    private String unitType;
}
