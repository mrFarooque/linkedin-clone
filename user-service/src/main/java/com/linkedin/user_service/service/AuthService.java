package com.linkedin.user_service.service;

import com.linkedin.user_service.dto.LoginRequestDto;
import com.linkedin.user_service.dto.SignupRequestDto;
import com.linkedin.user_service.dto.UserDto;

public interface AuthService {

     UserDto signUp(SignupRequestDto signupRequestDto);

     String login(LoginRequestDto loginRequestDto);
}
