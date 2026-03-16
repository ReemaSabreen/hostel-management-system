package com.hostel.hostel_management.dto;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;
    private String role;
    private Long userId;
    private Long studentId;
    
}
