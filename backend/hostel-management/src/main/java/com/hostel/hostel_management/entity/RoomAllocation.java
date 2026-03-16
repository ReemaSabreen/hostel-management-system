package com.hostel.hostel_management.entity;

import java.time.LocalDate;

import com.hostel.hostel_management.entity.enums.AllocationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "room_allocations")
@Data
public class RoomAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate allocationDate;

    private LocalDate deallocationDate;

    @Enumerated(EnumType.STRING)
    private AllocationStatus status;

    private LocalDate createdAt;

}
