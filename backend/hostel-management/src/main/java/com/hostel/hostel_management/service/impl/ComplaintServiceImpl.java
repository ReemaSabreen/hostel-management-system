package com.hostel.hostel_management.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Complaint;
import com.hostel.hostel_management.entity.enums.ComplaintStatus;
import com.hostel.hostel_management.repository.ComplaintRepository;
import com.hostel.hostel_management.service.ComplaintService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService{

    private final ComplaintRepository complaintRepository;

    @Override
    public Complaint raiseComplaint(Complaint complaint) {
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setCreatedAt(LocalDateTime.now());
        return complaintRepository.save(complaint);
        
    }

    @Override
    public List<Complaint> getStudentComplaints(Long studentId) {
       return complaintRepository.findByStudentStudentId(studentId);
    }

    @Override
    public Complaint updateComplaintStatus(Long complaintId, ComplaintStatus status) {
       Complaint complaint = complaintRepository.findById(complaintId)
       .orElseThrow(() -> new RuntimeException("Complaint not found"));
       complaint.setStatus(status);
        if(status == ComplaintStatus.RESOLVED){
            complaint.setResolvedAt(LocalDateTime.now());
        }
        return complaintRepository.save(complaint);

    }

    @Override
    public List<Complaint> getAllComplaints(){
        return complaintRepository.findAll();

    }
    

}
