package com.enigma.loanapp.controller;

import com.enigma.loanapp.constant.AppPath;
import com.enigma.loanapp.model.request.AuthRequest;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH_BASE)
public class AuthController {

    private final AuthService authService;

    @PostMapping(AppPath.REGISTER)
    public ResponseEntity<CommonResponse<?>> register(@Validated @RequestBody RegisterCustomerRequest customerRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(authService.registerCustomer(customerRequest))
                                .build()
                );
    }
    @PostMapping(AppPath.ADMIN + AppPath.REGISTER)
    public ResponseEntity<?> registerAdmin(@Validated @RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(authService.registerAdmin(request))
                                .build()
                );
    }
    @PostMapping(AppPath.STAFF + AppPath.REGISTER)
    public ResponseEntity<?> registerStaff(@Validated @RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(authService.registerStaff(request))
                                .build()
                );
    }


    @PostMapping(AppPath.LOGIN)
    public ResponseEntity<?> login(@Validated @RequestBody AuthRequest authRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(authService.login(authRequest))
                                .build()
                );
    }
}