package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Notification;
import com.hostel.hostel_management.entity.Student;

public interface NotificationService {


    void createNotification(Student student, String message);

    List<Notification> getStudentNotifications(Long StudentId);

    void markAsRead(Long studentId);
    
}
