package com.hostel.hostel_management.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostel.hostel_management.dto.LoginRequestDTO;
import com.hostel.hostel_management.dto.LoginResponse;
import com.hostel.hostel_management.entity.User;
import com.hostel.hostel_management.repository.StudentRepository;
import com.hostel.hostel_management.repository.UserRepository;
import com.hostel.hostel_management.security.JwtUtil;
import com.hostel.hostel_management.service.AuthService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;

    @Override
    public User register(User user){
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setIsActive(true);
        return userRepository.save(user);
    }
    @Override
    public LoginResponse login(LoginRequestDTO request){
        User user = userRepository.findByUsername(request.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPasswordHash())){
            throw new RuntimeException("Invalid Password");
        }
        String token = jwtUtil.generateToken(user.getUsername(),user.getRole().name());
        LoginResponse response = new LoginResponse();

        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setUserId(user.getUserId());

    studentRepository.findByUser(user).ifPresent(student -> response.setStudentId(student.getStudentId()));
        return response;

        
    }


    @Override
    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPasswordHash(newPassword);
        userRepository.save(user);
    }
    @Override
    public void forgotPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPasswordHash(newPassword);
        userRepository.save(user);
    }

    @Override
    public void setUserActiveStatus(Long userId, boolean status){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(status);
        userRepository.save(user);
    }

    @Override
    public User updateProfile(Long userId, User updatedUser) {

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

    user.setUsername(updatedUser.getUsername());
    user.setEmail(updatedUser.getEmail());

    return userRepository.save(user);
    }
    @Override
    public boolean verifyToken(String token) {

        try {
        jwtUtil.extractUsername(token);
        return true;
        } catch (Exception e) {
        return false;
        }
    }

    @Override
    public List<User> getStudentsWithoutProfile(){
        return userRepository.findStudentUsersWithoutProfile();
    }



}