package ru.nikitaloh.practice.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

public class KafkaObjectDeserializer implements Deserializer<KafkaTableEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public KafkaTableEvent deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            return objectMapper.readValue(data, KafkaTableEvent.class);
        } catch (Exception e) {
            System.out.println("Ошибка");
            return new KafkaTableEvent();
        }
    }
}
