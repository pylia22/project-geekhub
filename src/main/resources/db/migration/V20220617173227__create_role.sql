create table role
(
    id   serial
        constraint role_pk
            primary key,
    role varchar not null
);

alter table role
    owner to postgres;

create unique index role_id_uindex
    on role (id);

create unique index role_role_name_uindex
    on role (role);

