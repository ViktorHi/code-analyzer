package com.vicras.codeanalyzerserver.service;

import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;

public interface AuthService {
    UserResponseDto signIn(SignInDto signInDto);
}
