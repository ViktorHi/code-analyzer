package com.vicras.codeanalyzerserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static com.vicras.codeanalyzerserver.model.BaseEntity.ALLOCATION_SIZE;
import static com.vicras.codeanalyzerserver.model.BaseEntity.SEQUENCE_GENERATOR;


@Getter
@Setter
@Entity
@Table(name = "document")
@SequenceGenerator(
        name = SEQUENCE_GENERATOR, sequenceName = "SEQ_DOCUMENT", allocationSize = ALLOCATION_SIZE
)
public class Document extends BaseEntity {
    @ManyToOne()
    private AnalyzerUser user;

    @Embedded
    private DocumentSource source;
    private String title;
    private String statistics;
}
