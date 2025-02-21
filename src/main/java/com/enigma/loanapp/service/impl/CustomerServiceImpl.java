package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.request.SearchCustomerRequest;
import com.enigma.loanapp.model.request.UpdateCustomerRequest;
import com.enigma.loanapp.model.response.CustomerResponse;
import com.enigma.loanapp.repository.CustomerRepository;
import com.enigma.loanapp.service.CustomerService;
import com.enigma.loanapp.specification.CustomerSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(AppUser appUser) {
        Customer customer = new Customer();
        customer.setUser(appUser);

        return customerRepository.save(customer);
    }

    @Override
    public Customer createCustomer(AppUser appUser, RegisterCustomerRequest request) {
        Customer customer = Customer.builder()
                .phone(request.getPhone())
                .dateOfBirth(request.getDateOfBirth())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .status(true)
                .user(appUser)
                .build();

        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest request) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(request);
        return customerRepository.findAll(specification);
    }

    @Override
    public Customer update(Customer customer) {
        findByIdOrThrowNotFound(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        customerRepository.softDeleteById(id);
    }

    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("customer not found"));
    }
}