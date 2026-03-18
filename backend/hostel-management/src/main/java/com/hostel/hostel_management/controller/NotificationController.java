package com.hostel.hostel_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.entity.Notification;
import com.hostel.hostel_management.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{studentId}")
    public ResponseEntity<List<Notification>> getStudentNotifications(@PathVariable Long studentId){
        return ResponseEntity.ok(notificationService.getStudentNotifications(studentId));
    }

    @PutMapping("/read/{studentId}")
    public void markAsRead(@PathVariable Long studentId){
        notificationService.markAsRead(studentId);
    }
    
}
