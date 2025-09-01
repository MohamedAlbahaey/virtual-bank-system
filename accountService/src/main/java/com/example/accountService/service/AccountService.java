package com.example.accountService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.accountService.dtos.AccountTransferRequest;
import com.example.accountService.dtos.UserResponse;
import com.example.accountService.enums.AccountStatus;
import com.example.accountService.exception.BadRequestException;
import com.example.accountService.exception.NotFoundException;
import com.example.accountService.model.Accounts;
import com.example.accountService.repo.AccountRepositery;

import jakarta.transaction.Transactional;

@Service
public class AccountService {

    @Autowired
    AccountRepositery accountRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Transactional
    public String transferBetweenAccounts(AccountTransferRequest request) {
        Accounts fromAccount = accountRepo.findById(request.getFromAccountId()).orElse(null);
        Accounts toAccount = accountRepo.findById(request.getToAccountId()).orElse(null);
        double amount = request.getAmount();

        if (fromAccount == null || toAccount == null)
            throw new NotFoundException("Account not found");

        if (fromAccount.getBalance() < amount || fromAccount.getStatus() == AccountStatus.INACTIVE
                || toAccount.getStatus() == AccountStatus.INACTIVE)
            throw new BadRequestException("Invalid transfer request");

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        fromAccount.setUpdatedAt(LocalDateTime.now());
        toAccount.setUpdatedAt(LocalDateTime.now());

        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);

        return "Account updated successfully";
    }

    public Accounts createAccount(Accounts account) {

        UserResponse user = webClientBuilder.build()
                .get().uri("http://localhost:8090/users/{userId}/profile", account.getUserId()).retrieve()
                .bodyToMono(UserResponse.class)
                .block();

        if (user == null) {
            throw new NotFoundException("User with ID " + account.getUserId() + " not found.");
        }

        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            account.setAccountNumber(generateAccountNumber());
        }

        if (account.getBalance() < 0 || account.getAccountType() == null) {
            throw new BadRequestException("Invalid account type or initial balance");
        }

        return accountRepo.save(account);
    }

    private String generateAccountNumber() {
        return "ACCT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public Accounts getAccount(UUID accountId) {
        return accountRepo.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account with ID " + accountId + " not found"));
    }

    public List<Accounts> getAllAccountsFromUser(UUID userId) {
        List<Accounts> accounts = accountRepo.findAllByUserId(userId);
        if (accounts == null || accounts.isEmpty()) {
            throw new NotFoundException("No accounts found for user ID " + userId);
        }
        return accounts;
    }
}
