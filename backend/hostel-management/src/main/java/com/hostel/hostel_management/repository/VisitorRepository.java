package com.hostel.hostel_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    List<Visitor> findByStudentStudentId(Long studentId);
    
}
