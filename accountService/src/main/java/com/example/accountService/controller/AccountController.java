package com.example.accountService.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.accountService.dtos.AccountCreation;
import com.example.accountService.dtos.AccountDetail;
import com.example.accountService.dtos.AccountResponse;
import com.example.accountService.dtos.AccountTransferRequest;
import com.example.accountService.dtos.AccountTransferResponse;
import com.example.accountService.kafka.LogProducer;
import com.example.accountService.mappers.AccountMapper;
import com.example.accountService.service.AccountService;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    private final AccountMapper accountMapper;

    private final LogProducer logProducer;

    public AccountController(AccountMapper accountMapper, LogProducer logProducer) {
        this.accountMapper = accountMapper;
        this.logProducer = logProducer;
    }

    @PutMapping("/accounts/transfer")
    public ResponseEntity<AccountTransferResponse> transferBetweenAccounts(
            @RequestBody AccountTransferRequest request) {

        var response = accountService.transferBetweenAccounts(request);

        return ResponseEntity.ok(accountMapper.toAccountTransferResponse(response));
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountCreation request,
            UriComponentsBuilder uriBuilder) {
        var account = accountService.createAccount(accountMapper.toAccount(request));

        var uri = uriBuilder.path("/accounts/{accountId}").buildAndExpand(account.getId()).toUri();

        AccountResponse response = accountMapper.toAccountResponse(account);
        response.setMessage("Account created successfully");

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountDetail> getAccount(@PathVariable UUID accountId) {
        var account = accountService.getAccount(accountId);

        logProducer.sendLog(account, "Response");

        return ResponseEntity.ok(accountMapper.toAccountDetail(account));
    }

    @GetMapping("/users/{userId}/accounts")
    public ResponseEntity<List<AccountDetail>> getAllAccountsFromUser(@PathVariable UUID userId) {

        var accounts = accountService.getAllAccountsFromUser(userId);

        return ResponseEntity.ok(accountMapper.toAccountDetail(accounts));
    }

    @GetMapping("/accounts/")
    public String homePage() {
        return "Account Service";
    }
}
