package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Visitor;

public interface VisitorService {
    Visitor checkInVisitor(Visitor visitor);
    Visitor checkOuVisitor (Long visitorId);
    List<Visitor> getStudentVisitors(Long studentId);
}
