package com.vicras.codeanalyzerserver.exception.exceptions.format;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;

public class PaginationException extends AnalyzerException {
    public PaginationException(String message) {
        super(message);
    }
}