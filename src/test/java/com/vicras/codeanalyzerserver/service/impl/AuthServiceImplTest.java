package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.exception.exceptions.business.UserExistException;
import com.vicras.codeanalyzerserver.generator.SignInGenerator;
import com.vicras.codeanalyzerserver.generator.UserGenerator;
import com.vicras.codeanalyzerserver.mapper.UserMapper;
import com.vicras.codeanalyzerserver.repository.AnalyzerUserRepository;
import com.vicras.codeanalyzerserver.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.vicras.codeanalyzerserver.generator.UserGenerator.getUser;
import static java.util.Optional.empty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {

    private final UserMapper userMapper = mock(UserMapper.class);
    private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
    private final AnalyzerUserRepository repository = mock(AnalyzerUserRepository.class);
    private final UserService userService = mock(UserService.class);
    private final JWTService jwtService = mock(JWTService.class);

    private final AuthServiceImpl authService = new AuthServiceImpl(userMapper, passwordEncoder,
            repository, userService, jwtService);

    @Test
    public void signInSuccess(){
        //given
        var signInDto = SignInGenerator.getSignInDto();

        //when
        when(repository.findByLogin(signInDto.getLogin())).thenReturn(empty());

        //then
        authService.signIn(signInDto);
        verify(repository, times(1)).findByLogin(signInDto.getLogin());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void signInFail(){
        //given
        var signInDto = SignInGenerator.getSignInDto();

        //when
        when(repository.findByLogin(signInDto.getLogin())).thenReturn(Optional.of(getUser()));

        //then
        Assertions.assertThrows(UserExistException.class, () -> authService.signIn(signInDto));
        verify(repository, times(1)).findByLogin(signInDto.getLogin());
        verify(repository, times(0)).save(any());
    }
}