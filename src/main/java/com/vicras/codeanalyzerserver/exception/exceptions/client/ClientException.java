package com.vicras.codeanalyzerserver.exception.exceptions.client;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;

public abstract class ClientException extends AnalyzerException {
    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ClientException(String msg) {
        super(msg);
    }
}