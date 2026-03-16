package com.hostel.hostel_management.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.MessAttendance;
import com.hostel.hostel_management.entity.MessMenu;



public interface MessService {
    MessMenu createMenu(MessMenu menu);
    List<MessMenu> getMenuByDate(LocalDate date);
    MessAttendance markAttendance(MessAttendance attendance);
    BigDecimal calculateMessBill(Long studentId);
    Fee generateMessFee(Long studentId);
}
