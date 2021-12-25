package com.vicras.codeanalyzerserver.exception.exceptions.business;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;

public class BusinessException extends AnalyzerException {
    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Exception e) {
        super(e);
    }
}