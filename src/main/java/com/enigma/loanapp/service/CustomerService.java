package com.enigma.loanapp.service;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.AuthRequest;
import com.enigma.loanapp.model.request.SearchCustomerRequest;
import com.enigma.loanapp.model.request.UpdateCustomerRequest;
import com.enigma.loanapp.model.response.CustomerResponse;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    Customer create(AppUser appUser);
    Customer createCustomer(AppUser appUser, RegisterCustomerRequest request);
    Customer getCustomerById(String id);
    List<Customer> getAll(SearchCustomerRequest request);
    Customer update(Customer customer);
    void delete(String id);

}