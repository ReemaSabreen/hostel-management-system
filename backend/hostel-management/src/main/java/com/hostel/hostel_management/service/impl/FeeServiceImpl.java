package com.hostel.hostel_management.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.enums.FeeStatus;
import com.hostel.hostel_management.repository.FeeRepository;
import com.hostel.hostel_management.service.FeeService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService{

    private final FeeRepository feeRepository;


    @Override
    public Fee createFee(Fee fee) {
        fee.setCreatedAt(LocalDateTime.now());
        if(fee.getDueDate() == null){
            fee.setDueDate(LocalDate.now().plusDays(30));
        }
        fee.setStatus(FeeStatus.PENDING);
        return feeRepository.save(fee);
    }

    @Override
    public List<Fee> getStudentFees(Long studentId) {
        return feeRepository.findByStudentStudentId(studentId);
    }

    @Override
    public Fee updateFee(Long feeId,Fee updatedFee){
        Fee fee = feeRepository.findById(feeId)
        .orElseThrow(() -> new RuntimeException("Fee not  found"));

        fee.setAmount(updatedFee.getAmount());
        fee.setDueDate(updatedFee.getDueDate());
        fee.setFeeType(updatedFee.getFeeType());
        return feeRepository.save(fee);
    }
    @Override
    public List<Fee> getAllFees(){
        return feeRepository.findAll();
    }

    @Override
    public Fee getByFeeId(Long feeId){
        return feeRepository.findById(feeId)
        .orElseThrow(() -> new RuntimeException("Fee not found"));
    }

    @Override
    public List<Fee> getDefaulters() {
       List<Fee> fees =  feeRepository.findByStatus(FeeStatus.PENDING);
       for(Fee fee:fees){
        if(fee.getDueDate().isBefore(LocalDate.now())){
            fee.setStatus(FeeStatus.OVERDUE);
            feeRepository.save(fee);
        }
       }
       return feeRepository.findByStatus(FeeStatus.OVERDUE);

    }
    
}
