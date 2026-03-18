package com.hostel.hostel_management.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Complaint;
import com.hostel.hostel_management.entity.enums.ComplaintStatus;
import com.hostel.hostel_management.repository.ComplaintRepository;
import com.hostel.hostel_management.service.ComplaintService;
import com.hostel.hostel_management.service.NotificationService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ComplaintServiceImpl implements ComplaintService{

    private final ComplaintRepository complaintRepository;
    private final NotificationService notificationService;

    @Override
    public Complaint raiseComplaint(Complaint complaint) {
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setCreatedAt(LocalDateTime.now());
        String ticket = "CMP-"+System.currentTimeMillis();
        complaint.setTicketNo(ticket);
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
        Complaint savedComplaint = complaintRepository.save(complaint);
        
        notificationService.createNotification(complaint.getStudent(), "Complaint status updated : "+complaint.getTicketNo()+" is "+complaint.getStatus());
        return savedComplaint;

    }

    @Override
    public List<Complaint> getAllComplaints(){
        return complaintRepository.findAll();

    }
    

}
