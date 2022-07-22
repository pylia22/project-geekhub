create table feedback
(
    id        serial
        constraint feedback_pk
            primary key,
    event_id  integer   not null
        constraint feedback_event_id_fk
            references event,
    user_name varchar   not null,
    message   text      not null,
    date      timestamp not null
);

alter table feedback
    owner to postgres;

create unique index feedback_id_uindex
    on feedback (id);

