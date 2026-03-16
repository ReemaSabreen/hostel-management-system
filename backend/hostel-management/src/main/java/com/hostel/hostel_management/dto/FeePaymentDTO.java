package com.hostel.hostel_management.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FeePaymentDTO {
    private Long feeId;
    private Long studentId;
    private BigDecimal amount;
    private String paymentMethod;
}
