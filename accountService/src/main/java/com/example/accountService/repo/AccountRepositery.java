package com.example.accountService.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.accountService.model.Accounts;

@Repository
public interface AccountRepositery extends JpaRepository<Accounts, UUID> {

    public List<Accounts> findAllByUserId(UUID userId);

    @Query("SELECT a FROM Accounts a WHERE a.status = 'ACTIVE' AND a.updatedAt < :cutoff")
    List<Accounts> findStaleActiveAccounts(LocalDateTime cutoff);
}
