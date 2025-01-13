package com.amit.user_service.service;


import com.amit.user_service.dto.CreateUserRequestDto;
import com.amit.user_service.entity.User;
import com.amit.user_service.event.UserCreatedEvent;
import com.amit.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    private  final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public void createUser(CreateUserRequestDto createUserRequestDto){
        User user=modelMapper.map(createUserRequestDto, User.class);

        User savedUser=userRepository.save(user);

        UserCreatedEvent userCreatedEvent=modelMapper.map(savedUser,UserCreatedEvent.class);
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC,userCreatedEvent.getId(),userCreatedEvent);
    }
}
