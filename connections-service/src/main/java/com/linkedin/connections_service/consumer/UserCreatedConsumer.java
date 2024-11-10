package com.linkedin.connections_service.consumer;

import com.linkedin.connections_service.service.ConnectionsService;
import com.linkedin.user_service.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreatedConsumer {
    private final ConnectionsService connectionsService;

    @KafkaListener(topics = "user-created-topic")
    public void handleUserCreated(UserCreatedEvent userCreatedEvent) {
        // create person in db
        log.info("handling user created event with create person: {}", userCreatedEvent);
        connectionsService.createPerson(userCreatedEvent.getUserId(), userCreatedEvent.getUsername());
        log.info("person created successfully");
    }
}
