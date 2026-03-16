package com.hostel.hostel_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Visitor;
import com.hostel.hostel_management.repository.VisitorRepository;
import com.hostel.hostel_management.service.VisitorService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {
    
    private final VisitorRepository visitorRepository;

    @Override
    public Visitor checkInVisitor(Visitor visitor) {
        visitor.setCheckInTime(LocalDateTime.now());
       return visitorRepository.save(visitor);
    }

    @Override
    public Visitor checkOuVisitor(Long visitorId) {
        Visitor visitor = visitorRepository.findById(visitorId)
        .orElseThrow(() -> new RuntimeException("visitor not found"));
        visitor.setCheckOutTime(LocalDateTime.now());
        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> getStudentVisitors(Long studentId) {
        return visitorRepository.findByStudentStudentId(studentId);
    }
    
}
