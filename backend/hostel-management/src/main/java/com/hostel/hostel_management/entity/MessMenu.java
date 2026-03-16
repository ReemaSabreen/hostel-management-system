package com.hostel.hostel_management.entity;

import java.time.LocalDate;


import org.hibernate.annotations.CreationTimestamp;

import com.hostel.hostel_management.entity.enums.MealType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "mess_menu")
@Data

public class MessMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String menuItems;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;
    

    
}
