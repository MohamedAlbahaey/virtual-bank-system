package com.example.transactionService.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.transactionService.dtos.TransactionDetail;
import com.example.transactionService.dtos.TransferRequestExecution;
import com.example.transactionService.dtos.TransferRequestInitiation;
import com.example.transactionService.dtos.TransferResponse;
import com.example.transactionService.model.Transaction;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    List<TransactionDetail> toTransactionDetail(List<Transaction> transaction);

    Transaction toTransaction(TransferRequestInitiation requestIntitation);

    Transaction toTransaction(TransferRequestExecution requestExecution);

    TransferResponse toTransferResponse(Transaction transaction);
}
