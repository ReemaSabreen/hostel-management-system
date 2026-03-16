package com.hostel.hostel_management.service;

import java.util.List;

import com.hostel.hostel_management.dto.LoginRequestDTO;
import com.hostel.hostel_management.dto.LoginResponse;
import com.hostel.hostel_management.entity.User;

public interface AuthService {
    User register(User user);

    void resetPassword(String email, String newPassword);
    void forgotPassword(String email, String newPassword);
    void setUserActiveStatus(Long userId, boolean status);
    User updateProfile(Long userId, User updatedUser);
    boolean verifyToken(String token);

    LoginResponse login(LoginRequestDTO request);

    List<User> getStudentsWithoutProfile();

}
