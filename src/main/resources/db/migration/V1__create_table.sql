DROP TABLE if exists series;
CREATE TABLE series
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255),
    description       TEXT,
    created_timestamp timestamptz,
    updated_timestamp timestamptz
);

DROP TABLE if exists category;
CREATE TABLE category
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255),
    description       TEXT,
    series            VARCHAR(255),
    created_timestamp timestamptz,
    updated_timestamp timestamptz
);

DROP TABLE if exists product;
CREATE TABLE product
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(255),
    description       TEXT,
    series            VARCHAR(255),
    category          VARCHAR(255),
    image_link        TEXT,
    created_timestamp timestamptz,
    updated_timestamp timestamptz
);
