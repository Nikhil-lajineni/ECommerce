CREATE TABLE category
(
    id              BIGINT       NOT NULL,
    created_at      datetime     NULL,
    last_updated_at datetime     NULL,
    is_deleted      BIT(1)       NULL,
    name            VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE jt_instructor
(
    user_id           BIGINT       NOT NULL,
    favourite_student VARCHAR(255) NULL,
    CONSTRAINT pk_jt_instructor PRIMARY KEY (user_id)
);

CREATE TABLE jt_mentor
(
    user_id BIGINT NOT NULL,
    rating  BIGINT NULL,
    CONSTRAINT pk_jt_mentor PRIMARY KEY (user_id)
);

CREATE TABLE jt_user
(
    id    BIGINT       NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_jt_user PRIMARY KEY (id)
);

CREATE TABLE product
(
    id              BIGINT       NOT NULL AUTO_INCREMENT,
    created_at      datetime     NULL,
    last_updated_at datetime     NULL,
    is_deleted      BIT(1)       NULL,
    title           VARCHAR(255) NULL,
    price           DOUBLE       NULL,
    category_id     BIGINT       NULL,
    `description`   VARCHAR(255) NULL,
    image           VARCHAR(255) NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE single_table
(
    id                BIGINT       NOT NULL,
    user_type         INT          NULL,
    name              VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    rating            BIGINT       NULL,
    favourite_student VARCHAR(255) NULL,
    CONSTRAINT pk_single_table PRIMARY KEY (id)
);

ALTER TABLE jt_instructor
    ADD CONSTRAINT FK_JT_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE jt_mentor
    ADD CONSTRAINT FK_JT_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES jt_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);