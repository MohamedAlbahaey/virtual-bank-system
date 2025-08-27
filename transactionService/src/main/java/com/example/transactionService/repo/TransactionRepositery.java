package com.example.transactionService.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.transactionService.model.Transaction;

@Repository
public interface TransactionRepositery extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByFromAccountId(UUID fromAccountId);

    List<Transaction> findAllByToAccountId(UUID toAccountId);
}
