package com.example.transactionService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.transactionService.dtos.AccountDetail;
import com.example.transactionService.dtos.AccountTransferResponse;
import com.example.transactionService.enums.AccountStatus;
import com.example.transactionService.enums.TransactionStatus;
import com.example.transactionService.exception.BadRequestException;
import com.example.transactionService.exception.NotFoundException;
import com.example.transactionService.model.Transaction;
import com.example.transactionService.repo.TransactionRepositery;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepositery transactionRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public List<Transaction> getTransactionHistory(UUID accountId) {
        List<Transaction> transactionsFromAccount = transactionRepo.findAllByFromAccountId(accountId);
        List<Transaction> transactionsToAccount = transactionRepo.findAllByToAccountId(accountId);

        if (transactionsFromAccount == null || transactionsToAccount == null) {
            throw new NotFoundException("No transactions found for account ID " + accountId);
        }

        List<Transaction> transactionHistory = transactionsFromAccount;
        transactionHistory.addAll(transactionsToAccount);

        return transactionHistory;
    }

    public Transaction initiateTransfer(Transaction request) {
        AccountDetail fromAccount = webClientBuilder.build()
                .get().uri("http://localhost:8091/accounts/{accountId}", request.getFromAccountId()).retrieve()
                .bodyToMono(AccountDetail.class)
                .block();

        AccountDetail toAccount = webClientBuilder.build()
                .get().uri("http://localhost:8091/accounts/{accountId}", request.getToAccountId()).retrieve()
                .bodyToMono(AccountDetail.class)
                .block();

        if (fromAccount == null || toAccount == null || fromAccount.getStatus() == AccountStatus.INACTIVE
                || toAccount.getStatus() == AccountStatus.INACTIVE || fromAccount.getBalance() < request.getAmount())
            throw new BadRequestException("Invalid 'from' or 'to' account ID or insufficient funds");

        return transactionRepo.save(request);
    }

    public Transaction executeTransfer(UUID transactionId) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new BadRequestException("Transaction not initiated"));

        AccountTransferResponse message = webClientBuilder.build()
                .put()
                .uri("http://localhost:8091/accounts/transfer")
                .bodyValue(transaction)
                .retrieve()
                .bodyToMono(AccountTransferResponse.class)
                .block();

        if (message == null) {
            transaction.setStatus(TransactionStatus.FAILED);
            return transactionRepo.save(transaction);
        }

        transaction.setStatus(TransactionStatus.SUCCESS);
        return transactionRepo.save(transaction);
    }
}
