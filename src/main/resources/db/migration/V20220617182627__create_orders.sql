create table orders
(
    id       serial
        constraint order_pk
            primary key,
    event_id integer                                             not null
        constraint order_event_id_fk
            references event
            on update cascade on delete cascade,
    user_id  integer
        constraint order_users_id_fk
            references users
            on update cascade on delete cascade,
    quantity integer                                             not null
);

alter table orders
    owner to postgres;

create unique index order_id_uindex
    on orders (id);

