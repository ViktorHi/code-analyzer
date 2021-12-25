package com.vicras.codeanalyzerserver.exception.exceptions.client;

import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import lombok.Getter;

@Getter
public class TargetServiceException extends ClientException {
    private final ResponseError responseError;

    public TargetServiceException(ResponseError responseError) {
        super(responseError.getErrorMessage());
        this.responseError = responseError;
    }
}