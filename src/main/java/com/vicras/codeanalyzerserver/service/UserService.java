package com.vicras.codeanalyzerserver.service;

import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    AnalyzerUser findUserByLogin(String login);
}
