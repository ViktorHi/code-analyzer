package com.vicras.codeanalyzerserver.exception.handler;

import com.vicras.codeanalyzerserver.exception.exceptions.AnalyzerException;
import com.vicras.codeanalyzerserver.exception.handler.custom.CustomExceptionsHandler;
import com.vicras.codeanalyzerserver.exception.handler.system.ExceptionsHandler;
import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionsHandler handlerFactory;
    private final CustomExceptionsHandler customExceptionsFactory;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> exceptionHandler(Exception exception) {
        log.warn(exception.getMessage(), exception);
        return handlerFactory.handle(exception);
    }

    @ExceptionHandler(AnalyzerException.class)
    public ResponseEntity<ResponseError> exceptionHandler(AnalyzerException exception) {
        log.warn(exception.getMessage(), exception);
        return customExceptionsFactory.handle(exception);
    }
}