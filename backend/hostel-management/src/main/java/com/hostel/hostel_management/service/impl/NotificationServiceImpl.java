package com.hostel.hostel_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Notification;
import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.repository.NotificationRepository;
import com.hostel.hostel_management.service.NotificationService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    public void createNotification(Student student, String message){
        Notification notification = new Notification();
        notification.setStudent(student);
        notification.setMessage(message);
        notification.setRead(false);
        notification.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(notification);

    }

    @Override
    public List<Notification> getStudentNotifications(Long studentId){
        return notificationRepository.findByStudentStudentIdOrderByCreatedAtDesc(studentId);

    }

    @Override
    public void markAsRead(Long studentId){
        List<Notification> notifications = notificationRepository.findByStudentStudentIdAndIsReadFalse(studentId);
        for(Notification notification:notifications){
            notification.setRead(true);

        }
        notificationRepository.saveAll(notifications);

    }
    }

    
    

