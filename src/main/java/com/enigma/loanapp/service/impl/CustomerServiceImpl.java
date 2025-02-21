package com.enigma.loanapp.service.impl;

import com.enigma.loanapp.entity.AppUser;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.RegisterCustomerRequest;
import com.enigma.loanapp.model.request.UpdateCustomerRequest;
import com.enigma.loanapp.model.response.CustomerResponse;
import com.enigma.loanapp.repository.CustomerRepository;
import com.enigma.loanapp.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}

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
        return customerRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new DataNotFoundException("Customer not found"));
    }

    @Override
    public CustomerResponse getById(String id) {
        return mapToRes(this.getCustomerById(id));
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAllByStatusTrue();
        return customers.stream().map(this::mapToRes).toList();
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = this.getCustomerById(request.getId());

        customer.setPhone(request.getPhone());
        customer.setStatus(request.getStatus() == 1);
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setDateOfBirth(request.getDateOfBirth());

        return mapToRes(customerRepository.save(customer));
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(String id) {
        customerRepository.softDeleteById(id);
    }

    // Mapper method moved inside the service class
    private CustomerResponse mapToRes(Customer customer) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(customer.getDateOfBirth());

        return CustomerResponse.builder()
                .id(customer.getId())
                .dateOfBirth(date)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .status(customer.getStatus() ? "ACTIVE" : "NON-ACTIVE")
                .build();
    }
}
