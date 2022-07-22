create table city
(
    id        serial
        constraint city_pk
            primary key,
    city_name varchar                                              not null
);

alter table city
    owner to postgres;

create unique index city_id_uindex
    on city (id);

