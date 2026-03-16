package com.hostel.hostel_management.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(unique = true)
    private String enrollmentNo;

    private String firstName;

    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;

    private String phone;
    private String emergencyContact;

    private String course;

    private Integer year;

    private String photoUrl;

    private String address;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private LocalDate createdAt;

}
