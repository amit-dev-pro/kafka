package com.amit.notification_service.consumer;


import com.amit.user_service.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "user-created-topic")
    public void handleUserCreatedTopic(UserCreatedEvent userCreatedEvent){
        log.info("handleUserCreatedTopic: {}",userCreatedEvent);
    }

    @KafkaListener(topics = "user-random-topic",groupId = "groupId123")
    public void handleUserRandomTopic1(String message){
        log.info("handleUserRandomTopic1: {}",message);
    }

    @KafkaListener(topics = "user-random-topic",groupId = "groupId123")
    public void handleUserRandomTopic2(String message){
        log.info("handleUserRandomTopic2: {}",message);
    }

    @KafkaListener(topics = "user-random-topic",groupId = "groupId123")
    public void handleUserRandomTopic3(String message){
        log.info("handleUserRandomTopic3: {}",message);
    }
}
