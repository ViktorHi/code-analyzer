package com.vicras.codeanalyzerserver.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.vicras.codeanalyzerserver.constant.TimeConstants.ERROR_DATE_TIME_FORMAT;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError {

    private Integer errorCode;
    private String errorMessage;

    @JsonFormat(pattern = ERROR_DATE_TIME_FORMAT)
    private LocalDateTime timeStamp;
}
