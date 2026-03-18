package com.hostel.hostel_management.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>{
    List<Notification> findByStudentStudentIdOrderByCreatedAtDesc(Long studentId);

    List<Notification> findByStudentStudentIdAndIsReadFalse(Long studentId);

    
}
