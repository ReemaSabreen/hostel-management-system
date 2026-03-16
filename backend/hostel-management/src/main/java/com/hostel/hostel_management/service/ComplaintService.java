package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Complaint;
import com.hostel.hostel_management.entity.enums.ComplaintStatus;

public interface ComplaintService {
    Complaint raiseComplaint(Complaint complaint);

    List<Complaint> getStudentComplaints(Long studentId);
    Complaint updateComplaintStatus(Long complaintId, ComplaintStatus status);

    List<Complaint> getAllComplaints();
}
