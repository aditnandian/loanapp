package com.enigma.loanapp.service;

import com.enigma.loanapp.model.request.AuthRequest;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.response.LoginResponse;
import com.enigma.loanapp.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(RegisterCustomerRequest request);
    RegisterResponse registerAdmin(AuthRequest authRequest);
    RegisterResponse registerStaff(AuthRequest authRequest);
    LoginResponse login(AuthRequest authRequest);
}