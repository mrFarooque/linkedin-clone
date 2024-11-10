package com.linkedin.user_service.service.impl;

import com.linkedin.user_service.dto.LoginRequestDto;
import com.linkedin.user_service.dto.SignupRequestDto;
import com.linkedin.user_service.dto.UserDto;
import com.linkedin.user_service.entity.User;
import com.linkedin.user_service.event.UserCreatedEvent;
import com.linkedin.user_service.exception.BadRequestException;
import com.linkedin.user_service.exception.ResourceNotFoundException;
import com.linkedin.user_service.repository.UserRepository;
import com.linkedin.user_service.service.AuthService;
import com.linkedin.user_service.service.JwtService;
import com.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    @Value("${kafka.topic.user-created-topic}")
    public String KAFKA_USER_CREATED_TOPIC;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    public UserDto signUp(SignupRequestDto signupRequestDto) {
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if(exists) {
            throw new BadRequestException("User already exists with this email, cannot signup again.");
        }

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);

        // generate user-created event so that connections can be created
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .username(savedUser.getName())
                .userId(savedUser.getId()).build();
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+loginRequestDto.getEmail()));

        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        if(!isPasswordMatch) {
            throw new BadRequestException("Incorrect password");
        }

        return jwtService.generateAccessToken(user);
    }
}
