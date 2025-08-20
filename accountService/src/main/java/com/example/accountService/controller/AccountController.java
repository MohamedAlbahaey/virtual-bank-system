package com.example.accountService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.accountService.dtos.TransferRequest;
import com.example.accountService.dtos.TransferResponse;

@RestController
public class AccountController {

    public ResponseEntity<TransferResponse> transferBetweenAccounts(@RequestBody TransferRequest request) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts/")
    public String homePage() {
        return "Account Service";
    }
}
