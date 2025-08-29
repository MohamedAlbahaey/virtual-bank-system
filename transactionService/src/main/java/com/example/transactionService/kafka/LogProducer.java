package com.example.transactionService.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LogProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public LogProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String logMessageJson) {
        kafkaTemplate.send("logging-topic", logMessageJson);
        System.out.println("✅ Sent log to Kafka: " + logMessageJson);
    }
}
