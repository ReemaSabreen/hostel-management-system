package com.hostel.hostel_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.entity.Complaint;
import com.hostel.hostel_management.entity.enums.ComplaintStatus;
import com.hostel.hostel_management.service.ComplaintService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @PostMapping("/raise")
    public ResponseEntity<Complaint> raiseComplaint(@RequestBody Complaint complaint){
        return ResponseEntity.ok(complaintService.raiseComplaint(complaint));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Complaint>> getStudentComplaints(@PathVariable Long studentId){
        return ResponseEntity.ok(complaintService.getStudentComplaints(studentId));
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints(){
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }


    @PutMapping("/{complaintId}/status")
    public ResponseEntity<Complaint> updateStatus(@PathVariable Long complaintId, @RequestParam ComplaintStatus status){
        return ResponseEntity.ok(complaintService.updateComplaintStatus(complaintId,status));
    }
    
}
