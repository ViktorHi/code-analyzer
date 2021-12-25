package com.vicras.codeanalyzerserver.controller.rest;

import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;
import com.vicras.codeanalyzerserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-in")
    public UserResponseDto signIn(@Validated @RequestBody SignInDto dto) {
        return authService.signIn(dto);
    }
}