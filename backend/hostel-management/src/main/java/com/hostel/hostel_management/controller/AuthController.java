package com.hostel.hostel_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostel.hostel_management.dto.LoginRequestDTO;
import com.hostel.hostel_management.dto.LoginResponse;
import com.hostel.hostel_management.entity.User;
import com.hostel.hostel_management.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));

    } 

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword){
        authService.resetPassword(email,newPassword);
        return ResponseEntity.ok("Password reset successful");
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email,
                                        @RequestParam String newPassword) {

    authService.forgotPassword(email, newPassword);

    return ResponseEntity.ok("Password updated successfully");
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam boolean status){
        authService.setUserActiveStatus(id, status);
        return ResponseEntity.ok("User status updated");
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(authService.updateProfile(id,user));
    }

    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestParam String token){
        
        return ResponseEntity.ok(authService.verifyToken(token));
    }

    @GetMapping("/students-without-profile")
    public ResponseEntity<List<User>> getStudentsWithoutProfile(){
        return ResponseEntity.ok(authService.getStudentsWithoutProfile());
    }

   

}
