package com.vicras.codeanalyzerserver.exception.handler.custom;

import com.vicras.codeanalyzerserver.exception.exceptions.business.EntityNotFoundException;
import com.vicras.codeanalyzerserver.exception.exceptions.security.AuthenticationException;
import com.vicras.codeanalyzerserver.exception.handler.BaseExceptionHandler;
import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class CustomExceptionsHandler extends BaseExceptionHandler {

    @Getter
    private final Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ResponseError>>> handleExceptionMap = Map.of(
            EntityNotFoundException.class, exc -> handleEntityNotFoundException((EntityNotFoundException) exc),
            AuthenticationException.class, this::handleAccessForbiddenException
    );

    private ResponseEntity<ResponseError> handleEntityNotFoundException(EntityNotFoundException exc) {
        return buildResponseError(exc.getMessage(), NOT_FOUND);
    }

    private ResponseEntity<ResponseError> handleAccessForbiddenException(Exception exc) {
        return buildResponseError(exc.getMessage(), FORBIDDEN);
    }

    @Override
    protected ResponseEntity<ResponseError> handleDefaultException(Exception exc) {
        return buildResponseError(exc.getMessage(), BAD_REQUEST);
    }
}
