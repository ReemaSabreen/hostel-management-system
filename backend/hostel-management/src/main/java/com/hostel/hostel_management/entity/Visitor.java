package com.hostel.hostel_management.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "visitors")
@Data
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitorId;
    @ManyToOne
    @JoinColumn(name = "student_id")    
    private Student student;

    private String VisitorName;

    private String VisitorPhone;
    private String relation;
    private String purpose;

    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private String idProofType;
    private String idProofNumber;

}
