package com.example.accountService.kafka;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LogProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendLog(Object payload, String messageType) {
        try {
            Map<String, Object> log = new HashMap<>();
            log.put("message", payload);
            log.put("messageType", messageType);
            log.put("dateTime", LocalDateTime.now().toString());

            String logMessageJson = objectMapper.writeValueAsString(log);

            kafkaTemplate.send("logging-topic", logMessageJson);
            System.out.println("✅ Sent log to Kafka: " + logMessageJson);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while serializing log", e);
        }
    }
}
