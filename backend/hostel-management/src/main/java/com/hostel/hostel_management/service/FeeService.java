package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Fee;

public interface FeeService {
    Fee createFee(Fee fee);
    List<Fee> getStudentFees(Long studentId);
    List<Fee> getDefaulters();
    Fee updateFee(Long feeId, Fee updatedFee);
    Fee getByFeeId(Long feeId);
    List<Fee> getAllFees();
}
