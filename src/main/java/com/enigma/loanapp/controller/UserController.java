package com.enigma.loanapp.controller;

import com.enigma.loanapp.constant.AppPath;
import com.enigma.loanapp.model.response.CommonResponse;
import com.enigma.loanapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(AppPath.USERS + AppPath.ID)
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        CommonResponse.builder()
                                .message("Success")
                                .data(userService.getById(id))
                                .build()
                );
    }
}