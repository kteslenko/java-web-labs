CREATE TABLE IF NOT EXISTS address
(
    id             bigserial PRIMARY KEY,
    cod_address    varchar(128),
    client_id      bigint      NOT NULL REFERENCES client ON DELETE CASCADE,
    city           varchar(64) NOT NULL,
    address        varchar(128),
    point_np       integer,
    point_ukr_post integer
);
