package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.entity.Room;
import com.hostel.hostel_management.entity.RoomAllocation;

public interface RoomService {
    Room createRoom(Room room);
    List<Room> getAvailableRooms();
    RoomAllocation allocateRoom(Long studentId, String roomNumber);
    void deallocateRoom(Long allocationId);
    List<Room> getAllRooms();
    Room getRoomById(Long roomId);
    Room updateRoom(Long roomId, Room room);
    void deleteRoom(Long roomId);
    List<RoomAllocation> getStudentAllocations(Long studentId);
    RoomAllocation getCurrentAllocation(Long studentId);
    List<RoomAllocation> getAllAllocations();
   
}
