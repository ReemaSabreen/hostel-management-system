package com.hostel.hostel_management.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hostel.hostel_management.entity.User;

public class UserDetailsImpl implements UserDetails{

    private String username;
    private String password;
    private String role;

    public UserDetailsImpl(User user){
        this.username = user.getUsername();
        this.password = user.getPasswordHash();
        this.role = user.getRole().name();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role));
    }

    @Override
    public  String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    
}
