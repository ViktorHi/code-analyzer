package com.vicras.codeanalyzerserver.exception.exceptions.business;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;
import com.vicras.codeanalyzerserver.model.BaseEntity;

import static com.vicras.codeanalyzerserver.constant.ErrorConstants.ERROR_ID_MSG_PATTERN;

public class EntityNotFoundException extends AnalyzerException {

    public EntityNotFoundException(Class<? extends BaseEntity> aClass, String id) {
        super(String.format(ERROR_ID_MSG_PATTERN, aClass.getSimpleName(), id));
    }
}