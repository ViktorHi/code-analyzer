package com.vicras.codeanalyzerserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class DocumentSource {
    private String link;

    @Enumerated(EnumType.STRING)
    private SourceType type;
}
