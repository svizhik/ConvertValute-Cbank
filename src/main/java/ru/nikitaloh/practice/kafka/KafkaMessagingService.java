package ru.nikitaloh.practice.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagingService {
    @Value("${topic.send-message}")
    private String topicCreateMessage;

    private final KafkaTemplate kafkaTemplate;

    public KafkaMessagingService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(KafkaTableEvent tableEvent) {
        kafkaTemplate.send(topicCreateMessage, tableEvent);
    }

}

// общая транзакция
