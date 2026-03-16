package com.hostel.hostel_management.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mess_attendance")
@Data
public class MessAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate date;
    private Boolean breakfast;
    
    private Boolean lunch;
    private Boolean dinner;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;
}
