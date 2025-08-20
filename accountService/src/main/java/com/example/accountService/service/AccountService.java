package com.example.accountService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.accountService.dtos.TransferRequest;
import com.example.accountService.model.Accounts;
import com.example.accountService.repo.AccountRepositery;

@Service
public class AccountService {

    @Autowired
    AccountRepositery accountRepo;

    public String transferBetweenAccounts(TransferRequest request) {
        Accounts fromAccount = accountRepo.findById(request.getFromAccountId()).orElse(null);
        Accounts toAccount = accountRepo.findById(request.getToAccountId()).orElse(null);
        if (fromAccount == null || toAccount == null) {
            return "Account not found";
        }
        return "";
    }

}
