package com.hostel.hostel_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.Payment;
import com.hostel.hostel_management.entity.enums.FeeStatus;
import com.hostel.hostel_management.repository.FeeRepository;
import com.hostel.hostel_management.repository.PaymentRepository;
import com.hostel.hostel_management.service.NotificationService;
import com.hostel.hostel_management.service.PaymentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final FeeRepository feeRepository;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public Payment makePayment(Long feeId, Payment payment){

        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee not found"));
        if(fee.getStatus() == FeeStatus.PAID){
            throw new RuntimeException("Fee already paid");
        }
        if(payment.getAmount().compareTo(fee.getAmount())<0){

            throw new RuntimeException("Insufficient payment amount");
        }

        payment.setFee(fee);
        payment.setStudent(fee.getStudent());
        payment.setPaymentDate(LocalDateTime.now());

        fee.setStatus(FeeStatus.PAID);
        feeRepository.save(fee);

        Payment savedPayment =  paymentRepository.save(payment);
       
        notificationService.createNotification(fee.getStudent(),"Fee paid : "+payment.getAmount()+" paid for Fee id : "+fee.getFeeId()+" type "+fee.getFeeType());

        return savedPayment;
    }

    @Override
    public List<Payment> getStudentPayments(Long studentId){
        return paymentRepository.findByStudentStudentId(studentId);
    }

    @Override
    public Payment getReceipt(Long paymentId){
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}