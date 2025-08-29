package com.example.bffService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.bffService.dtos.AccountDetail;
import com.example.bffService.dtos.Dashboard;
import com.example.bffService.dtos.TransactionDetail;

@Service
public class BffService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Dashboard getUserDashboard(UUID userId) {

        Dashboard user = webClientBuilder.build()
                .get().uri("http://localhost:8090/users/{userId}/profile", userId).retrieve()
                .bodyToMono(Dashboard.class)
                .block();

        List<AccountDetail> accounts = webClientBuilder.build()
                .get().uri("http://localhost:8091/users/{userId}/accounts", userId).retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AccountDetail>>() {
                })
                .block();

        for (AccountDetail a : accounts) {
            List<TransactionDetail> transactions = webClientBuilder.build()
                    .get().uri("http://localhost:8092/accounts/{accountId}/transactions", a.getId()).retrieve()
                    .bodyToMono(new ParameterizedTypeReference<List<TransactionDetail>>() {
                    })
                    .block();

            a.setTransactions(transactions);
        }

        user.setAccounts(accounts);

        return user;
    }
}
