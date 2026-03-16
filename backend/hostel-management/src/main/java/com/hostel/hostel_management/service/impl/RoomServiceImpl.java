package com.hostel.hostel_management.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hostel.hostel_management.entity.Room;
import com.hostel.hostel_management.entity.RoomAllocation;
import com.hostel.hostel_management.entity.Student;
import com.hostel.hostel_management.entity.enums.AllocationStatus;
import com.hostel.hostel_management.entity.enums.RoomStatus;
import com.hostel.hostel_management.repository.RoomAllocationRepository;
import com.hostel.hostel_management.repository.RoomRepository;
import com.hostel.hostel_management.repository.StudentRepository;
import com.hostel.hostel_management.service.RoomService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    
    
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;
    private final RoomAllocationRepository allocationRepository;
   
    @Override
    public Room createRoom(Room room) {
        room.setCurrentOccupancy(0);
        room.setStatus(RoomStatus.AVAILABLE);
        switch(room.getType()){
            case SINGLE:
                room.setCapacity(1);
                break;
            case DOUBLE:
                room.setCapacity(2);
                break;
            case TRIPLE:
                room.setCapacity(3);
                break;
            case QUAD:
                room.setCapacity(4);
                break;
        }

        
        return roomRepository.save(room);
    }
    @Override
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    @Override
    public Room getRoomById(Long roomId){
        return roomRepository.findById(roomId)
        .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    

    @Override
    public Room updateRoom(Long roomId, Room room){
        Room existing = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));
        existing.setRoomNumber(room.getRoomNumber());
        existing.setFloor(room.getFloor());
        existing.setType(room.getType());
        existing.setCapacity(room.getCapacity());
        existing.setCurrentOccupancy(room.getCurrentOccupancy());
        existing.setStatus(room.getStatus());
        existing.setRentPerMonth(room.getRentPerMonth());

        return roomRepository.save(existing);
    }

    @Override
    public void deleteRoom(Long roomId){
        roomRepository.deleteById(roomId);

    }
    @Override
    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatus(RoomStatus.AVAILABLE);
    }

    @Override
    public RoomAllocation allocateRoom(Long studentId, Long roomId) {
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));
        Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new RuntimeException("Room not found"));

        if(room.getCurrentOccupancy() >= room.getCapacity()){
            throw new RuntimeException("Room full");
        }

        boolean alreadyAllocated = allocationRepository.existsByStudentAndStatus(student,AllocationStatus.ACTIVE);

        if(alreadyAllocated){
            throw new RuntimeException("Student already has a room");
        }
        if(room.getStatus() == RoomStatus.MAINTENANCE){
            throw new RuntimeException("Room under maintenance");
        }

        RoomAllocation allocation = new RoomAllocation();
        allocation.setStudent(student);
        allocation.setRoom(room);
        allocation.setAllocationDate(LocalDate.now());
        allocation.setStatus(AllocationStatus.ACTIVE);
        allocation.setCreatedAt(LocalDate.now());
        allocationRepository.save(allocation);

        
        room.setCurrentOccupancy(room.getCurrentOccupancy() + 1);
        if(room.getCurrentOccupancy() == room.getCapacity()){
            room.setStatus(RoomStatus.OCCUPIED);
        }
        else{
            room.setStatus(RoomStatus.AVAILABLE);
        }
        roomRepository.save(room);
        return allocation;
    }

    @Override
    public void deallocateRoom(Long allocationId) {
        RoomAllocation allocation = allocationRepository.findById(allocationId)
        .orElseThrow(() -> new RuntimeException("Allacation not found"));

        if(allocation.getStatus() == AllocationStatus.COMPLETED){
        throw new RuntimeException("Room already vacated");
    }

        Room room = allocation.getRoom();
        
        allocation.setStatus(AllocationStatus.COMPLETED);
        allocation.setDeallocationDate(LocalDate.now());
        allocationRepository.save(allocation);
        room.setCurrentOccupancy(room.getCurrentOccupancy() - 1);
        if(room.getCurrentOccupancy() < room.getCapacity()){
        room.setStatus(RoomStatus.AVAILABLE);
    }
        roomRepository.save(room);
    }

    @Override
    public List<RoomAllocation> getAllAllocations(){
        return allocationRepository.findAll();
    }

    @Override
    public List<RoomAllocation> getStudentAllocations(Long studentId){
        Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));

        return allocationRepository.findByStudent(student);
    }

    @Override
    public RoomAllocation getCurrentAllocation(Long studentId){
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new RuntimeException("Student not found"));

    return allocationRepository.findByStudentAndStatus(student, AllocationStatus.ACTIVE)
        .orElseThrow(() -> new RuntimeException("No active allocation"));
}

    
}
