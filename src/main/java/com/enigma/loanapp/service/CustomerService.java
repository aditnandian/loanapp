package com.enigma.loanapp.service;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.AuthRequest;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.request.UpdateCustomerRequest;
import com.enigma.loanapp.model.response.CustomerResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    Customer create(AppUser appUser);
    Customer createCustomer(AppUser appUser, RegisterCustomerRequest request);
    Customer getCustomerById(String id);
    CustomerResponse getById(String id);
    List<CustomerResponse> getAll();
    CustomerResponse update(UpdateCustomerRequest request);
    void delete(String id);

}