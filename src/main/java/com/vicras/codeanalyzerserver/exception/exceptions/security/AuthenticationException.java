package com.vicras.codeanalyzerserver.exception.exceptions.security;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;

public class AuthenticationException extends AnalyzerException {
    public AuthenticationException(String message) {
        super(message);
    }
}
