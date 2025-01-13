package com.amit.user_service.configs;


import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_RANDOM_TOPIC;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;

    @Bean
    public NewTopic createTopic(){
        return new NewTopic(KAFKA_RANDOM_TOPIC,3,(short) 1);

    }

    @Bean
    public NewTopic userCreatedTopic(){
        return new NewTopic(KAFKA_USER_CREATED_TOPIC,3,(short)1);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
