package ru.warmhouse.control.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

@Entity
@Table(name = "device")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Device {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "connected")
    private Boolean connected;

    @Column(name = "active")
    private Boolean enabled;

    @Column(name = "target_value")
    private String targetValue;

    @Column(name = "actual_value")
    private String actualValue;

    @Column(name = "date_time_updated")
    private ZonedDateTime dateTimeUpdated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private DeviceType deviceType;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "device_group_id")
    private Long deviceGroupId;
}
