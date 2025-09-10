package com.example.springkafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(String topicName,String message) {
        try{
            kafkaTemplate.send(topicName,message);
        }catch(Exception e){
            return "Error message: " + e.getMessage();
        }
        return "Produced message: " + message;
    }
}
