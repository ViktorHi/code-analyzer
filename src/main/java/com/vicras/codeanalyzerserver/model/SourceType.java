package com.vicras.codeanalyzerserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

import static java.lang.String.format;

@Getter(onMethod_ = {@JsonValue})
@RequiredArgsConstructor
public enum SourceType {

    INTERNAL("Internal"),
    GIT("Git");

    private final String title;

    public static Optional<SourceType> findByTitle(String expression) {
        return Arrays.stream(SourceType.values())
                .filter(fieldName -> fieldName.title.equalsIgnoreCase(expression))
                .findFirst();
    }

    @JsonCreator
    public static SourceType getByTitle(String expression) {
        return SourceType.findByTitle(expression)
                .orElseThrow(() -> new IllegalArgumentException(format("Document source %s not defined", expression)));
    }
}
