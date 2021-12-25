package com.vicras.codeanalyzerserver.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorConstants {

    // Exceptions
    public static final String ERROR_ID_MSG_PATTERN = "Не найден объект %s с id %d";
    public static final String ERROR_NAME_MSG_PATTERN = "Не найден объект %s с именем %s";
    public static final String INVALID_REQUEST_ERROR_MSG = "Введенные вами данные не соответсвуют формату";
    public static final String GLOBAL_ERRORS = "Глобальные ошибки: ";
    public static final String FIELD_FORMAT_ERROR = "Ошибка валидации";
    public static final String FIELD_ERRORS = "Ошибки валидации полей: ";

    // Client
    public static final String SERVICE_COMMUNICATION_EXCEPTION_MESSAGE = "Ошибка при взаимодействии между микросервисами, повторите снова или свяжитесь с администратором";

}