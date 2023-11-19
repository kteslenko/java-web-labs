CREATE TABLE IF NOT EXISTS client
(
    id         bigserial PRIMARY KEY,
    first_name varchar(128) NOT NULL,
    last_name  varchar(128) NOT NULL,
    phone      varchar(16)  NOT NULL UNIQUE,
    email      varchar(128) NOT NULL UNIQUE,
    gender     gender       NOT NULL,
    is_vip     boolean      NOT NULL DEFAULT false
);
