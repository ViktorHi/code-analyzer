package com.vicras.codeanalyzerserver.constant;

import lombok.experimental.UtilityClass;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class TimeConstants {

    public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    public static final DateTimeFormatter TENDER_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

    public static final String TENDER_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final DateTimeFormatter TENDER_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TENDER_DATE_TIME_FORMAT);

    public static final String ERROR_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final ZoneId MSK_ZONE_ID = ZoneId.of("Europe/Moscow");
    public static final ZoneOffset MSK_ZONE_OFFSET = ZoneOffset.of("+03:00");

    public static final String MOSCOW_TIMEZONE = "Europe/Moscow";
}