package com.example.loggingService.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.loggingService.enums.MessageType;
import com.example.loggingService.model.Logging;
import com.example.loggingService.repo.LoggingRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LogConsumer {

    private final LoggingRepository loggingRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LogConsumer(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    @KafkaListener(topics = "logging-topic", groupId = "logging-group")
    public void consume(String logMessageJson) {
        try {
            // Parse JSON
            JsonNode jsonNode = objectMapper.readTree(logMessageJson);

            String message = jsonNode.has("message") ? jsonNode.get("message").toString() : logMessageJson;
            String type = jsonNode.has("messageType") ? jsonNode.get("messageType").asText() : "Request";
            MessageType messageType = MessageType
                    .valueOf(type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase());

            Logging logging = new Logging();
            logging.setMessage(message);
            logging.setMessageType(messageType);

            loggingRepository.save(logging);

            System.out.println("✅ Log saved: " + message);

        } catch (Exception e) {
            System.err.println("❌ Failed to process log: " + e.getMessage());
        }
    }
}
