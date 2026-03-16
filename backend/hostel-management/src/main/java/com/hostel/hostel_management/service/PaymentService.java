package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Payment;

public interface PaymentService {
    Payment makePayment(Long feeId, Payment payment);
    List<Payment> getStudentPayments(Long studentId);
    Payment getReceipt(Long paymentId);
    

}
