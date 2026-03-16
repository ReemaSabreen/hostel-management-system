package com.hostel.hostel_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hostel.hostel_management.entity.Room;
import com.hostel.hostel_management.entity.RoomAllocation;
import com.hostel.hostel_management.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;
    

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }
    @PutMapping("/{roomId}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long roomId, @RequestBody Room room){
        return ResponseEntity.ok(roomService.updateRoom(roomId,room));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long roomId){
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok("Room deleted");
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> getAvailableRooms(){
        return ResponseEntity.ok(roomService.getAvailableRooms());
    }

    @PostMapping("/allocate")
    public ResponseEntity<RoomAllocation> allocateRoom(@RequestParam Long studentId, @RequestParam Long roomId){
        return ResponseEntity.ok(roomService.allocateRoom(studentId, roomId));
    }

    @DeleteMapping("/deallocate/{allocationId}")
    public ResponseEntity<String> deallocateRoom(@PathVariable Long allocationId){
        roomService.deallocateRoom(allocationId);
        return ResponseEntity.ok("room deallocated");
    }

    @GetMapping("/allocations")
    public ResponseEntity<List<RoomAllocation>> getAllAllocations(){
        return ResponseEntity.ok(roomService.getAllAllocations());
    }

    @GetMapping("/allocations/{studentId}")
    public ResponseEntity<List<RoomAllocation>> getStudentAllocations(@PathVariable Long studentId){
        return ResponseEntity.ok(roomService.getStudentAllocations(studentId));
    }

    @GetMapping("/allocations/current/{studentId}")
    public ResponseEntity<RoomAllocation> getCurrentAllocation(@PathVariable Long studentId) {

        RoomAllocation allocation = roomService.getCurrentAllocation(studentId);

        return ResponseEntity.ok(allocation);
    }
   
   


}
