package com.vicras.codeanalyzerserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static com.vicras.codeanalyzerserver.model.BaseEntity.ALLOCATION_SIZE;
import static com.vicras.codeanalyzerserver.model.BaseEntity.SEQUENCE_GENERATOR;

@Getter
@Setter
@Entity
@Table(name = "analyzer_user")
@SequenceGenerator(
        name = SEQUENCE_GENERATOR, sequenceName = "SEQ_ANALYZER_USER", allocationSize = ALLOCATION_SIZE
)
public class AnalyzerUser extends BaseEntity {

    private String name;
    private String surname;

    @Column(unique = true)
    private String login;
    private String password;
}
