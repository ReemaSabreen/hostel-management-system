package com.hostel.hostel_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.entity.User;



public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEnrollmentNo(String enrollmentNo);
    Optional<Student> findByUser(User user);
}
