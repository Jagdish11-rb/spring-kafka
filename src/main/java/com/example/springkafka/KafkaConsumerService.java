package com.example.springkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {


    @KafkaListener(topics = AppConstants.TOPIC_NAME, groupId = "my-group")
    public void consume(String message) {
        log.info("Message received for {} topic : {}",AppConstants.TOPIC_NAME,message);
    }

}
