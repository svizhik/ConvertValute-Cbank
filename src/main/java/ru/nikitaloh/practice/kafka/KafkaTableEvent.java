package ru.nikitaloh.practice.kafka;

public class KafkaTableEvent {
    private KafkaOperationType operationType;
    private KafkaTableName tableName;
    private String change;

    public KafkaTableEvent() {

    }

    public KafkaTableEvent (KafkaOperationType operationType, KafkaTableName tableName, String change) {
        this.operationType = operationType;
        this.tableName = tableName;
        this.change = change;
    }

    @Override
    public String toString() {
        return "KafkaTableEvent{" +
                "operationType=" + operationType +
                ", tableName=" + tableName +
                ", change='" + change + '\'' +
                '}';
    }
}
