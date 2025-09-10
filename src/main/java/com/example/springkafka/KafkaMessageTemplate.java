package com.example.springkafka;

import lombok.Data;

@Data
public class KafkaMessageTemplate {

    private String topicName;
    private String message;
}
