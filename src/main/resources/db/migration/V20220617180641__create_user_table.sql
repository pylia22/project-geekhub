create table users
(
    id         serial
        constraint users_pk
            primary key,
    first_name varchar           not null,
    last_name  varchar           not null,
    email      varchar           not null,
    password   varchar           not null,
    login      varchar           not null,
    role_id    integer default 1 not null

);

alter table users
    owner to postgres;

create unique index users_email_uindex
    on users (email);

create unique index users_login_uindex
    on users (login);