package com.hostel.hostel_management.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.hostel.hostel_management.entity.enums.RoomStatus;
import com.hostel.hostel_management.entity.enums.RoomType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "rooms")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(unique = true)
    private String roomNumber;

    private Integer floor;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Integer capacity;

    private Integer currentOccupancy;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Column(name="rent_per_month")
    private BigDecimal rentPerMonth;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
