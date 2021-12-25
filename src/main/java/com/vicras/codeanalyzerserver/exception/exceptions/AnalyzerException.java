package com.vicras.codeanalyzerserver.exception.exceptions;

public abstract class AnalyzerException extends RuntimeException {

    public AnalyzerException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AnalyzerException(String msg) {
        super(msg);
    }

    public AnalyzerException(Throwable cause) {
        super(cause);
    }
}