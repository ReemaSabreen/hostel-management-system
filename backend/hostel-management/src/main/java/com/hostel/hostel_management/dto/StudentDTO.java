package com.hostel.hostel_management.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentDTO {
    private Long userId;
    private String enrollmentNo;
    private String firstName;
    private String lastName;
    private String phone;
    private String emergencyContact;
    private String course;
    private String year;
    private String photoUrl;
    private String address;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    
    
}
