package com.linkedin.user_service.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long userId;
    private String username;
}
