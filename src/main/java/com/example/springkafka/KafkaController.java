package com.example.springkafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private KafkaProducerService producerService;

    @Autowired
    private AdminClient adminClient;


    @GetMapping("/publish")
    public String publishMessage(@RequestBody KafkaMessageTemplate kafkaMessageTemplate) {
        try{
            if(topicExists(kafkaMessageTemplate.getTopicName())){
                return producerService.sendMessage(kafkaMessageTemplate.getTopicName(), kafkaMessageTemplate.getMessage());
            }else{
                return "Topic not found.";
            }
        }catch(Exception e){
            return "Unexpected error : "+e.getMessage();
        }

    }

    @DeleteMapping("/delete-topic")
    public String deleteTopic(@RequestParam("topicName") String topicName) throws ExecutionException, InterruptedException {
        if(topicExists(topicName)){
            adminClient.deleteTopics(Collections.singleton(topicName)).all().get();
        }
        return "Topic "+topicName+ " deleted successfully.";
    }

    public boolean topicExists(String topicName) throws ExecutionException, InterruptedException {
        return adminClient.listTopics().names().get().contains(topicName);
    }
}
