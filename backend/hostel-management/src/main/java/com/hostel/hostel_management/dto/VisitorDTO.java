package com.hostel.hostel_management.dto;

import lombok.Data;

@Data
public class VisitorDTO {
    private Long studentId;
    private String visitorName;
    private String visitorPhone;
    private String relation;
    private String purpose;
    private String idProofType;
    private String idProofNumber;
    
}
