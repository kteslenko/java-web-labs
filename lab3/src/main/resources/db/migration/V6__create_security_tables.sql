CREATE TABLE IF NOT EXISTS users
(
    id       bigserial PRIMARY KEY,
    username varchar(128) NOT NULL UNIQUE,
    password varchar(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities
(
    id        bigserial PRIMARY KEY,
    user_id   bigint       NOT NULL REFERENCES users ON DELETE CASCADE,
    authority varchar(128) NOT NULL,
    UNIQUE (user_id, authority)
);
