package com.vicras.codeanalyzerserver.constant;


import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationConstants {

    //tender request dto validation
    public static final String FUTURE_OR_PRESENT_DATE_MESSAGE = "Дата должна быть в настоящнм или будущем";
    public static final String LESS_THEN_255_SYMBOLS_MESSAGE = "Не более 255 символов";
    public static final String ZERO_MIN_VALUE_MESSAGE = "Минимальное значение - 0";
    public static final String NOT_BLANK_MESSAGE = "Не должно быть пустым";
    public static final String BEGIN_CONFIRM_EARLY_END_CONFIRM_MESSAGE = "Дата в поле beginConfirm должна быть раньше даты с поля endConfirm!";
    public static final String END_CONFIRM_EARLY_DATE_FROM_MESSAGE = "Дата в поле endConfirm должна быть раньше даты с поля dateFrom!";
    public static final String DATE_FROM_EARLY_DATE_TO_MESSAGE = "Дата в поле dateFrom должна быть раньше даты с поля dateTo!";
}
