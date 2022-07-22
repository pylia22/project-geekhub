create table event
(
    id          serial
        constraint event_pk
            primary key,
    name        varchar          not null,
    category    varchar          not null,
    description varchar,
    date        timestamp        not null,
    price       double precision not null,
    city_id     integer          not null
        constraint event_city_id_fk
            references city,
    image       bytea
);

alter table event
    owner to postgres;

create unique index event_id_uindex
    on event (id);

