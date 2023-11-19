TRUNCATE TABLE users CASCADE;

INSERT INTO users (username, password)
VALUES ('user', '$2a$10$1Z/z12IHOl3LLPt0preQgek6K.m/6nPDD.SA4JRWsMe.Z/USMi00G'),
       ('admin', '$2a$10$hXRrLLVrNobq8Vm.zzkbAOZrby0Bzz47.R0qhxMAIy5UyOegVoz8.');

INSERT INTO authorities (user_id, authority)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');
