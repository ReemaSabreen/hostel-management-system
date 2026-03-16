package com.hostel.hostel_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.Payment;

import com.hostel.hostel_management.service.FeeService;
import com.hostel.hostel_management.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/fees")
@RequiredArgsConstructor
public class FeeController {

    private final FeeService feeService;
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Fee> createFee(@RequestBody Fee fee){
        return ResponseEntity.ok(feeService.createFee(fee));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Fee>> getStudentFees(@PathVariable long studentId){
        return ResponseEntity.ok(feeService.getStudentFees(studentId));
    }

    @GetMapping
    public ResponseEntity<List<Fee>> getAllFees(){
        return ResponseEntity.ok(feeService.getAllFees());
    }

    @GetMapping("/defaulters")
    public ResponseEntity<List<Fee>> getDefaulters(){
    return ResponseEntity.ok(feeService.getDefaulters());
    }

    @PostMapping("/payments/{feeId}")
    public ResponseEntity<Payment> makePayment(@PathVariable Long feeId,
                                               @RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.makePayment(feeId, payment));
    }

    @GetMapping("/payments/student/{studentId}")
    public ResponseEntity<List<Payment>> getStudentPayments(@PathVariable Long studentId){
        return ResponseEntity.ok(paymentService.getStudentPayments(studentId));
    }

    @GetMapping("/payments/receipt/{paymentId}")
    public ResponseEntity<Payment> getReceipt(@PathVariable Long paymentId){
        return ResponseEntity.ok(paymentService.getReceipt(paymentId));
    }

    @PutMapping("/{feeId}")
    public ResponseEntity<Fee> updateFee(@PathVariable Long feeId,
                                         @RequestBody Fee fee){
        return ResponseEntity.ok(feeService.updateFee(feeId, fee));
    }
    
    
    @GetMapping("/{feeId}")
    public ResponseEntity<Fee> getFeeById(@PathVariable Long feeId){
        return ResponseEntity.ok(feeService.getByFeeId(feeId));
    }
    
    
}
