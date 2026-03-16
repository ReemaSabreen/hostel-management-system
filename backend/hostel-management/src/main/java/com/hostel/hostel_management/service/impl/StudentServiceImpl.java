package com.hostel.hostel_management.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.repository.StudentRepository;
import com.hostel.hostel_management.service.StudentService;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Student createStudent(Student student) {
        
        student.setCreatedAt(LocalDate.now());
        student.setCheckInDate(LocalDate.now());
        return studentRepository.save(student);
        
    }

    @Override
    public Student updateStudent(Long studentId, Student student) {
        Student existing = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not Found"));
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setDateOfBirth(student.getDateOfBirth());
        existing.setGender(student.getGender());
        existing.setPhone(student.getPhone());
        existing.setEmergencyContact(student.getEmergencyContact());
        existing.setCourse(student.getCourse());
        existing.setYear(student.getYear());
        existing.setAddress(student.getAddress());
        
        
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long StudentId) {
        studentRepository.deleteById(StudentId);
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findByEnrollmentNo(String enrollmentNo) {
        return studentRepository.findByEnrollmentNo(enrollmentNo);
    }
    @Override
    public Student checkoutStudent(Long studentId){

    Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new RuntimeException("Student not found"));

    student.setCheckOutDate(LocalDate.now());

    return studentRepository.save(student);
}

    
}
