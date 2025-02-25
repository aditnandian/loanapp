package com.enigma.loanapp.controller;

import com.enigma.loanapp.constant.AppPath;
import com.enigma.loanapp.model.request.InstalmentTypeRequest;
import com.enigma.loanapp.model.request.UpdateInstalmentTypeRequest;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.service.InstalmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.INSTALMENT_TYPE)
public class InstalmentTypeController {

    private final InstalmentTypeService instalmentTypeService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> create(@Validated @RequestBody InstalmentTypeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .message("Instalment type created successfully")
                        .data(instalmentTypeService.create(request))
                        .build()
                );
    }

    @GetMapping(AppPath.ID)
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Success")
                        .data(instalmentTypeService.getInstalmentTypeById(id))
                        .build()
                );
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Success")
                        .data(instalmentTypeService.getAll())
                        .build()
                );
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> update(@Validated @RequestBody UpdateInstalmentTypeRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Instalment type updated successfully")
                        .data(instalmentTypeService.update(request))
                        .build()
                );
    }

    @DeleteMapping(AppPath.ID)
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<?> delete(@PathVariable String id) {
        instalmentTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .message("Instalment type deleted successfully")
                        .build()
                );
    }
}
