package com.hostel.hostel_management.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;


import com.hostel.hostel_management.entity.Fee;
import com.hostel.hostel_management.entity.MessAttendance;
import com.hostel.hostel_management.entity.MessMenu;
import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.entity.enums.FeeStatus;
import com.hostel.hostel_management.entity.enums.FeeType;
import com.hostel.hostel_management.repository.FeeRepository;
import com.hostel.hostel_management.repository.MessAttendanceRepository;
import com.hostel.hostel_management.repository.MessMenuRepository;
import com.hostel.hostel_management.repository.StudentRepository;
import com.hostel.hostel_management.service.MessService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MessServiceImpl implements MessService{
    private final MessAttendanceRepository messAttendanceRepository;
    private final MessMenuRepository messMenuRepository;
    private final FeeRepository feeRepository;
    private final StudentRepository studentRepository;

    private static final int BREAKFAST_PRICE = 30;
    private static final int LUNCH_PRICE = 50;
    private static final int DINNER_PRICE = 60;

    @Override
    public MessMenu createMenu(MessMenu menu) {
        return messMenuRepository.save(menu);
    }

    @Override
    public List<MessMenu> getMenuByDate(LocalDate date) {
       return messMenuRepository.findByDate(date);
    }

    @Override
    public MessAttendance markAttendance(MessAttendance attendance) {
        return messAttendanceRepository.save(attendance);
    }

    @Override
    public BigDecimal calculateMessBill(Long studentId){
        List<MessAttendance> records = messAttendanceRepository.findByStudentStudentId(studentId);

        int breakfast = 0;
        int lunch = 0;
        int dinner = 0;
        for(MessAttendance record:records){
            if(Boolean.TRUE.equals(record.getBreakfast()))
                breakfast++;
            if(Boolean.TRUE.equals(record.getLunch()))
                lunch++;
            if(Boolean.TRUE.equals(record.getDinner()))
                dinner++;
        }
            int total = (breakfast*BREAKFAST_PRICE)+(lunch*LUNCH_PRICE)+(dinner*DINNER_PRICE);
            return BigDecimal.valueOf(total);

        
    }

    @Override
    public Fee generateMessFee(Long studentId){
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        BigDecimal amount = calculateMessBill(studentId);
        boolean exists = feeRepository.existsByStudentAndFeeTypeAndStatus(
            student,
            FeeType.MESS,
            FeeStatus.PENDING
    );

    if(exists){
        throw new RuntimeException("Mess fee already generated");
    }
        Fee fee = new Fee();
        fee.setStudent(student);
        fee.setFeeType(FeeType.MESS);
        fee.setAmount(amount);
        fee.setDueDate(LocalDate.now().plusDays(30));
        fee.setStatus(FeeStatus.PENDING);

        return feeRepository.save(fee);
        
        
    }
    
}
