package com.example.loggingService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loggingService.model.Logging;

public interface LoggingRepository extends JpaRepository<Logging, Long> {

}
