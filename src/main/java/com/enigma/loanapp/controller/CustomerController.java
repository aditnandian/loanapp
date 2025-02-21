package com.enigma.loanapp.controller;

import com.enigma.loanapp.constant.AppPath;
import com.enigma.loanapp.entity.Customer;
import com.enigma.loanapp.model.request.SearchCustomerRequest;
import com.enigma.loanapp.model.request.UpdateCustomerRequest;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.CUSTOMER)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(customerService.getCustomerById(id))
                                .build()
                );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAllCustomers(
            @RequestParam(name = "name" , required = false) String name,
            @RequestParam(name = "mobilePhone" , required = false) String phoneNumber,
            @RequestParam(name = "birthDate" , required = false) String birthDate,
            @RequestParam(name = "status" , required = false) Boolean status
    ) {
        SearchCustomerRequest request =  SearchCustomerRequest.builder()
                .name(name)
                .mobilePhoneNUmber(phoneNumber)
                .birthDate(birthDate)
                .status(status)
                .build();
        return customerService.getAll(request);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping(AppPath.ID)
    public ResponseEntity<?> delete(@PathVariable String id) {
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data("Deleted")
                                .build()
                );
    }
}