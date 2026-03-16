package com.hostel.hostel_management.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.MessAttendance;
import com.hostel.hostel_management.entity.MessMenu;

import com.hostel.hostel_management.service.MessService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mess")
@RequiredArgsConstructor
public class MessController {

    private final MessService messService;

    @PostMapping("/menu")
    public ResponseEntity<MessMenu> createMenu(@RequestBody MessMenu menu){
        return ResponseEntity.ok(messService.createMenu(menu));
        
    }

    @GetMapping("/view-menu")
    public ResponseEntity<List<MessMenu>> getMenu(@RequestParam LocalDate date){
        return ResponseEntity.ok(messService.getMenuByDate(date));
    }

    @PostMapping("/mess-attendance")
    public ResponseEntity<MessAttendance> markAttendance(@RequestBody MessAttendance attendance){
        return ResponseEntity.ok(messService.markAttendance(attendance));
    }

    @GetMapping("/bill/{studentId}")
    public ResponseEntity<BigDecimal> calculateMessBill(@PathVariable Long studentId){
        return ResponseEntity.ok(messService.calculateMessBill(studentId));
    }

    @PostMapping("/generate-fee/{studentId}")
    public ResponseEntity<Fee> generateMessFee(@PathVariable Long studentId){
        return ResponseEntity.ok(messService.generateMessFee(studentId));
    }

    


}
