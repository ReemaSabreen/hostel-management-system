package com.hostel.hostel_management.service;

import java.util.List;
import java.util.Optional;

import com.hostel.hostel_management.entity.Student;

public interface StudentService {
    Student createStudent(Student student);
    Student updateStudent(Long StudentId, Student student);
    void deleteStudent(Long StudentId);
    Student getStudentById(Long StudentId);
    List<Student> getAllStudents();
    Optional<Student> findByEnrollmentNo(String enrollmentNo);
    Student checkoutStudent(Long studentId);

    
    
    
}
