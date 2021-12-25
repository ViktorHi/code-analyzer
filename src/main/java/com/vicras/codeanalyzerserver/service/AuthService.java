package com.vicras.codeanalyzerserver.service;

import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import com.vicras.codeanalyzerserver.dto.auth.AuthTokenDto;
import com.vicras.codeanalyzerserver.dto.auth.CredentialsDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;

public interface AuthService {
    UserResponseDto signIn(SignInDto signInDto);

    AuthTokenDto login(CredentialsDto cred);
}
