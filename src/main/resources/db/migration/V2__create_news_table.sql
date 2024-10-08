drop table if exists news_column;
create table news_column
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255),
    description       TEXT,
    created_timestamp timestamptz,
    updated_timestamp timestamptz
);
drop table if exists news;
create table news
(
    id                SERIAL PRIMARY KEY,
    title             VARCHAR(255),
    content           TEXT,
    news_column       VARCHAR(255),
    created_timestamp timestamptz,
    updated_timestamp timestamptz
);