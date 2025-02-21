package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.model.response.UserResponse;
import com.enigma.loanapp.repository.AppUserRepository;
import com.enigma.loanapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUserId(String id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return AppUser.builder()
                .email(appUser.getUsername())
                .id(appUser.getId())
                .password(appUser.getPassword())
                .roles(appUser.getRoles())
                .build();
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByEmail(username).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        return AppUser.builder()
                .email(appUser.getUsername())
                .id(appUser.getId())
                .password(appUser.getPassword())
                .roles(appUser.getRoles())
                .build();
    }

    @Override
    public UserResponse getById(String id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        List<String> roles = appUser.getRoles().stream().map(role ->
                role.getRole().getRole().name()
        ).toList();

        return UserResponse.builder()
                .email(appUser.getUsername())
                .role(roles)
                .build();
    }
}
