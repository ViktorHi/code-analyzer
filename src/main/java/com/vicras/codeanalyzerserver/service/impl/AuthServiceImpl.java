package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import com.vicras.codeanalyzerserver.dto.auth.AuthTokenDto;
import com.vicras.codeanalyzerserver.dto.auth.CredentialsDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;
import com.vicras.codeanalyzerserver.exception.exceptions.AuthenticationException;
import com.vicras.codeanalyzerserver.exception.exceptions.business.UserExistException;
import com.vicras.codeanalyzerserver.mapper.UserMapper;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.repository.AnalyzerUserRepository;
import com.vicras.codeanalyzerserver.service.AuthService;
import com.vicras.codeanalyzerserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final AnalyzerUserRepository repository;
    private final UserService userService;
    private final JWTService jwtService;

    @Override
    public UserResponseDto signIn(SignInDto signInDto) {
        validateUserExist(signInDto.getLogin());
        var user = getNewUser(signInDto);
        repository.save(user);
        return mapper.toResponse(user);
    }

    private void validateUserExist(String login) {
        repository.findByLogin(login).ifPresent((ignore) -> {
                    throw new UserExistException("User already exist");
                }
        );
    }

    private AnalyzerUser getNewUser(SignInDto signInDto) {
        var user = new AnalyzerUser();
        user.setLogin(signInDto.getLogin());
        user.setPassword(encoder.encode(signInDto.getPassword()));
        user.setName(signInDto.getName());
        user.setSurname(signInDto.getSurname());
        return user;
    }

    @Override
    public AuthTokenDto login(CredentialsDto cred) {
        AnalyzerUser user = userService.findUserByLogin(cred.login);
        if (encoder.matches(cred.getPassword(), user.getPassword())) {
            return new AuthTokenDto(jwtService.getToken(user));
        }
        throw new AuthenticationException("Incorrect password or username");
    }
}
