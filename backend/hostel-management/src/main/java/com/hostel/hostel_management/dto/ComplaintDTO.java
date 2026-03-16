package com.hostel.hostel_management.dto;

import lombok.Data;

@Data
public class ComplaintDTO {

    private Long studentId;

    private String category;

    private String subject;

    private String description;

    private String priority;

}