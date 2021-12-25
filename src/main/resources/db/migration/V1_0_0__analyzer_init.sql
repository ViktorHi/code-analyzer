CREATE SEQUENCE IF NOT EXISTS SEQ_ANALYZER_USER START 1;
CREATE SEQUENCE IF NOT EXISTS SEQ_DOCUMENT START 1;


CREATE TABLE IF NOT EXISTS analyzer_user
(
    id         BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(255),
    surname    VARCHAR(255),
    login      VARCHAR(255),
    password   VARCHAR(255),
    CONSTRAINT pk_analyzer_user PRIMARY KEY (id)
);

ALTER TABLE analyzer_user
    ADD CONSTRAINT uc_analyzer_user_login UNIQUE (login);


CREATE TABLE IF NOT EXISTS document
(
    id         BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    user_id    BIGINT,
    statistics VARCHAR(32000),
    title      VARCHAR(255),
    link       VARCHAR(255),
    type       VARCHAR(255),
    CONSTRAINT pk_document PRIMARY KEY (id)
);

ALTER TABLE document
    ADD CONSTRAINT FK_DOCUMENT_ON_USER FOREIGN KEY (user_id) REFERENCES analyzer_user (id);