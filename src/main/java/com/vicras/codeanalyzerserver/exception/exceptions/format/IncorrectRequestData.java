package com.vicras.codeanalyzerserver.exception.exceptions.format;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;

public abstract class IncorrectRequestData extends AnalyzerException {
    protected IncorrectRequestData(String msg) {
        super(msg);
    }
}