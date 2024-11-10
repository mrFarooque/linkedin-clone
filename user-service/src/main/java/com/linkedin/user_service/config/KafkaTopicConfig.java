package com.linkedin.user_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-created-topic}")
    public String KAFKA_USER_CREATED_TOPIC;

    @Bean
    public NewTopic newTopic() {
        return new NewTopic(KAFKA_USER_CREATED_TOPIC, 3, (short) 1);
    }
}
