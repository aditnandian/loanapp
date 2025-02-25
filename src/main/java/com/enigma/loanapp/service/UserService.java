package com.enigma.loanapp.service;

import com.enigma.loanapp.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUserId(String id);
    UserResponse getById(String id);
}