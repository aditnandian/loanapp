package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.entity.Role;
import com.enigma.loanapp.entity.UserRole;
import com.enigma.loanapp.model.request.AuthRequest;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.response.LoginResponse;
import com.enigma.loanapp.model.response.RegisterResponse;
import com.enigma.loanapp.repository.AppUserRepository;
import com.enigma.loanapp.repository.UserRoleRepository;
import com.enigma.loanapp.security.JwtTokenProvider;
import com.enigma.loanapp.service.AuthService;
import com.enigma.loanapp.service.CustomerService;
import com.enigma.loanapp.service.RoleService;
import com.enigma.loanapp.util.enums.ERole;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRoleRepository userRoleRepository;
    private final AppUserRepository appUserRepository;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerCustomer(RegisterCustomerRequest request) {
        Role role = roleService.getOrSave(ERole.CUSTOMER);
        AppUser appUser = AppUser.builder()
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();

        UserRole userRole = UserRole.builder()
                .role(role)
                .build();
        appUser.setRoles(List.of(userRole));

        AppUser createdAppUser = appUserRepository.save(appUser);
        userRole.setAppUser(createdAppUser);
        userRoleRepository.save(userRole);

        customerService.createCustomer(createdAppUser, request);

        List<String> roles = new ArrayList<>();
        for (UserRole rolee : appUser.getRoles()) {
            roles.add(rolee.getRole().getRole().name());
        }

        return RegisterResponse.builder()
                .email(appUser.getEmail())
                .role(roles)
                .build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerAdmin(AuthRequest request) {
        Role roleCustomer = roleService.getOrSave(ERole.CUSTOMER);
        Role roleAdmin = roleService.getOrSave(ERole.ADMIN);
        Role roleStaff = roleService.getOrSave(ERole.STAFF);

        AppUser appUser = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        List<UserRole> roles = new ArrayList<>();
        roles.add(UserRole.builder().role(roleCustomer).build());
        roles.add(UserRole.builder().role(roleStaff).build());
        roles.add(UserRole.builder().role(roleAdmin).build());

        appUser.setRoles(roles);
        AppUser createdAppUser = appUserRepository.save(appUser);

        for (UserRole role : roles) {
            role.setAppUser(createdAppUser);
            userRoleRepository.save(role);
        }

        return buildRegisterResponse(createdAppUser);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse registerStaff(AuthRequest request) {
        Role roleCustomer = roleService.getOrSave(ERole.CUSTOMER);
        Role roleStaff = roleService.getOrSave(ERole.STAFF);

        AppUser appUser = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        List<UserRole> roles = new ArrayList<>();
        roles.add(UserRole.builder().role(roleCustomer).build());
        roles.add(UserRole.builder().role(roleStaff).build());

        appUser.setRoles(roles);
        AppUser createdAppUser = appUserRepository.save(appUser);

        for (UserRole role : roles) {
            role.setAppUser(createdAppUser);
            userRoleRepository.save(role);
        }

        return buildRegisterResponse(createdAppUser);
    }

    @Override
    public LoginResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail().toLowerCase(),
                        authRequest.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();

        // Extract username and role from AppUser
        String username = appUser.getEmail();
        String role = appUser.getRoles().get(0).getRole().getRole().name(); // Assuming user has at least one role

        String token = jwtTokenProvider.generateToken(username, role);

        return buildLoginResponse(appUser, token);
    }


    private RegisterResponse buildRegisterResponse(AppUser appUser) {
        List<String> roles = new ArrayList<>();
        for (UserRole role : appUser.getRoles()) {
            roles.add(role.getRole().getRole().name());
        }
        return RegisterResponse.builder()
                .email(appUser.getEmail())
                .role(roles)
                .build();
    }

    private LoginResponse buildLoginResponse(AppUser appUser, String token) {
        List<String> roles = new ArrayList<>();
        for (UserRole role : appUser.getRoles()) {
            roles.add(role.getRole().getRole().name());
        }
        return LoginResponse.builder()
                .email(appUser.getEmail())
                .token(token)
                .role(roles)
                .build();
    }
}
