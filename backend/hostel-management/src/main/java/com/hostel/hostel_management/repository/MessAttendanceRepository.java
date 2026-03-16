package com.hostel.hostel_management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.MessAttendance;

public interface MessAttendanceRepository extends JpaRepository<MessAttendance, Long> {
    List<MessAttendance> findByStudentStudentId(Long studentId);
    List<MessAttendance> findByDate(LocalDate date);
    
}
