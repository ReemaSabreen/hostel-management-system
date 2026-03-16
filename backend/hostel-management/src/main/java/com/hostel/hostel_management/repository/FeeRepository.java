package com.hostel.hostel_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.entity.enums.FeeStatus;
import com.hostel.hostel_management.entity.enums.FeeType;

public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByStudentStudentId(Long studentId);
    List<Fee> findByStatus(FeeStatus status);
    boolean existsByStudentAndFeeTypeAndStatus(Student student, FeeType feeType, FeeStatus status);
}
