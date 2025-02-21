package com.enigma.loanapp.controller;

import com.enigma.loanapp.constant.AppPath;
import com.enigma.loanapp.model.request.LoanTypeRequest;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.LOAN_TYPE)
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody LoanTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .message("Loan type created successfully")
                        .data(loanTypeService.create(request))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Success")
                        .data(loanTypeService.getById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Success")
                        .data(loanTypeService.getAll())
                        .build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody LoanTypeRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Loan type updated successfully")
                        .data(loanTypeService.update(id, request))
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        loanTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Loan type deleted successfully")
                        .data(null)
                        .build());
    }
}
