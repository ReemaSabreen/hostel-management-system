package com.hostel.hostel_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hostel.hostel_management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("""
            SELECT u FROM User u
            WHERE u.role = 'STUDENT'
            AND u.userId NOT IN (SELECT s.user.userId FROM Student s)
            """)
            List<User> findStudentUsersWithoutProfile();

}
