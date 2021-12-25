package com.vicras.codeanalyzerserver.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HttpConstants {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final int OK_CODE = 400;
    public static final String OK_MESSAGE = "Bad request";

    public static final int BAD_REQUEST_CODE = 400;
    public static final String BAD_REQUEST_MESSAGE = "Bad request";

    public static final int NOT_FOUND_CODE = 404;
    public static final String NOT_FOUND_MESSAGE = "Not found error";
}
