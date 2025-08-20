package com.example.accountService.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.accountService.model.Accounts;

public interface AccountRepositery extends JpaRepository<Accounts, UUID> {

}
