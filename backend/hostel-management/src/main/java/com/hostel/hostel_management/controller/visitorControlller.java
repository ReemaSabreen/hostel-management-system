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

import com.hostel.hostel_management.entity.Visitor;
import com.hostel.hostel_management.service.VisitorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/visitors")
@RequiredArgsConstructor
public class visitorControlller {
    private final VisitorService visitorService;

    @PostMapping("/chekin")
    public ResponseEntity<Visitor> checkIn(@RequestBody Visitor visitor){
        return ResponseEntity.ok(visitorService.checkInVisitor(visitor));
    }

    @PutMapping("/checkout/{id}")
    public ResponseEntity<Visitor> checkOut(@PathVariable Long id){
        return ResponseEntity.ok(visitorService.checkOuVisitor(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Visitor>> getStudentVisitors(@PathVariable Long studentId){
        return ResponseEntity.ok(visitorService.getStudentVisitors(studentId));
    }



    
}
