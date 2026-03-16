package com.hostel.hostel_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.RoomAllocation;
import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.entity.enums.AllocationStatus;

public interface RoomAllocationRepository extends JpaRepository<RoomAllocation, Long> {
    List<RoomAllocation> findByStudentStudentId(Long studentId);
    List<RoomAllocation> findByRoomRoomId(Long roomId);
    boolean existsByStudentAndStatus(Student student, AllocationStatus status);
    List<RoomAllocation> findByStudent(Student student);
    Optional<RoomAllocation> findByStudentAndStatus(Student student, AllocationStatus status);


}
