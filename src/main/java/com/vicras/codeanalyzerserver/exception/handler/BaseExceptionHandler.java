package com.vicras.codeanalyzerserver.exception.handler;

import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.function.Function;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public abstract class BaseExceptionHandler {

    public ResponseEntity<ResponseError> handle(Exception ex) {
        var map = getHandleExceptionMap();
        return handle(map, ex);
    }

    private ResponseEntity<ResponseError> handle(
            Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ResponseError>>> handleMap,
            Exception exc) {
        return handleMap.getOrDefault(exc.getClass(), this::handleDefaultException)
                .apply(exc);
    }

    protected ResponseEntity<ResponseError> handleDefaultException(Exception exc) {
        return buildResponseError(exc.getMessage(), INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ResponseError> buildResponseError(String message, HttpStatus status) {
        ResponseError errorBody = ResponseError.builder()
                .timeStamp(now())
                .errorCode(status.value())
                .errorMessage(message)
                .build();
        return new ResponseEntity<>(errorBody, status);
    }

    protected abstract Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ResponseError>>> getHandleExceptionMap();
}
