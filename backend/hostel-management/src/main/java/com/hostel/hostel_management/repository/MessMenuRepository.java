package com.hostel.hostel_management.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hostel.hostel_management.entity.MessMenu;

public interface MessMenuRepository extends JpaRepository<MessMenu, Long> {
    List<MessMenu> findByDate(LocalDate date);
    
}
