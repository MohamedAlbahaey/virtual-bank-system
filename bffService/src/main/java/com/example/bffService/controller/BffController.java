package com.example.bffService.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.bffService.dtos.Dashboard;
import com.example.bffService.service.BffService;

@RestController
public class BffController {
    @Autowired
    private BffService bffService;

    @GetMapping("/bff/dashboard/{userId}")
    public ResponseEntity<Dashboard> getUserDashboard(@PathVariable UUID userId) {
        return ResponseEntity.ok(bffService.getUserDashboard(userId));
    }

    @GetMapping("/bff")
    public String getHomePage() {
        return "BFF Service";
    }

}
