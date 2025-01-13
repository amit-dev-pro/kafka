package com.amit.user_service.controller;


import com.amit.user_service.dto.CreateUserRequestDto;
import com.amit.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


  private final KafkaTemplate<String,String> kafkaTemplate;
  private final UserService userService;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_TOPIC;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        userService.createUser(createUserRequestDto);
        return ResponseEntity.ok("User is created ");
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageController(@RequestBody String message) {

        for (int i = 0; i < 1000; i++) {
            kafkaTemplate.send(KAFKA_RANDOM_TOPIC,""+i%3,message+i);

        }
       return ResponseEntity.ok("message queue");
    }
}
