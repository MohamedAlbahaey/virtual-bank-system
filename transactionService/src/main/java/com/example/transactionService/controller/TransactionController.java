package com.example.transactionService.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.transactionService.dtos.TransactionDetail;
import com.example.transactionService.dtos.TransferRequestExecution;
import com.example.transactionService.dtos.TransferRequestInitiation;
import com.example.transactionService.dtos.TransferResponse;
import com.example.transactionService.mapper.TransactionMapper;
import com.example.transactionService.service.TransactionService;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionDetail>> getTransactionHistory(@PathVariable UUID accountId) {
        var response = transactionService.getTransactionHistory(accountId);

        List<TransactionDetail> transactionDetail = transactionMapper.toTransactionDetail(response);
        transactionDetail.forEach(t -> t.setAccountId(accountId));

        return ResponseEntity.ok(transactionDetail);
    }

    @PostMapping("/transactions/transfer/initiation")
    public ResponseEntity<TransferResponse> initiateTransfer(@RequestBody TransferRequestInitiation request) {
        var response = transactionService.initiateTransfer(transactionMapper.toTransaction(request));

        return ResponseEntity.ok(transactionMapper.toTransferResponse(response));
    }

    @PostMapping("/transactions/transfer/execution")
    public ResponseEntity<TransferResponse> executeTransfer(@RequestBody TransferRequestExecution request) {
        var response = transactionService.executeTransfer(request.getId());

        return ResponseEntity.ok(transactionMapper.toTransferResponse(response));
    }

    @GetMapping("/transactions")
    public String getHomePage() {
        return "Transaction Service";
    }

}
