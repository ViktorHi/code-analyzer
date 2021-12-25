package com.vicras.codeanalyzerserver.exception.handler.system;

import com.vicras.codeanalyzerserver.exception.handler.BaseExceptionHandler;
import com.vicras.codeanalyzerserver.exception.model.ResponseError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.util.Map;
import java.util.function.Function;

import static com.vicras.codeanalyzerserver.constant.ErrorConstants.INVALID_REQUEST_ERROR_MSG;
import static com.vicras.codeanalyzerserver.constant.ErrorConstants.SERVICE_COMMUNICATION_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Component
@RequiredArgsConstructor
public class ExceptionsHandler extends BaseExceptionHandler {

    private final ArgumentNotValidResolver argumentNotValidResolver;

    @Getter
    private final Map<Class<? extends Exception>, Function<Exception, ResponseEntity<ResponseError>>> handleExceptionMap = Map.of(
            MethodArgumentNotValidException.class, exc -> handleMethodArgumentNotValidException((MethodArgumentNotValidException) exc),
            HttpMessageNotReadableException.class, exc -> handleMessageNotReadable((HttpMessageNotReadableException) exc),
            WebClientRequestException.class, exc -> handleWebClientRequest((WebClientRequestException) exc)
    );

    // Target service not available
    private ResponseEntity<ResponseError> handleWebClientRequest(WebClientRequestException exc) {
        return buildResponseError(SERVICE_COMMUNICATION_EXCEPTION_MESSAGE, INTERNAL_SERVER_ERROR);
    }

    // Json parse error
    private ResponseEntity<ResponseError> handleMessageNotReadable(HttpMessageNotReadableException exc) {
        return buildResponseError(INVALID_REQUEST_ERROR_MSG, BAD_REQUEST);
    }

    // Validation exceptions
    private ResponseEntity<ResponseError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return buildResponseError(argumentNotValidResolver.resolve(ex), BAD_REQUEST);
    }
}