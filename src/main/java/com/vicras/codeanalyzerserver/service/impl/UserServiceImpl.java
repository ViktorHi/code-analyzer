package com.vicras.codeanalyzerserver.service.impl;


import com.vicras.codeanalyzerserver.exception.exceptions.business.EntityNotFoundException;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.repository.AnalyzerUserRepository;
import com.vicras.codeanalyzerserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AnalyzerUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        boolean accountNotExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNotLocked = true;
        boolean isUserActive = true;

        var user = findUserByLogin(email);

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                isUserActive,
                accountNotExpired,
                credentialsNotExpired,
                accountNotLocked,
                List.of(new SimpleGrantedAuthority("USER"))
        );
    }

    @Override
    public AnalyzerUser findUserByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException(AnalyzerUser.class, login));
    }

    @Override
    public AnalyzerUser findUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(AnalyzerUser.class, id.toString()));
    }
}
