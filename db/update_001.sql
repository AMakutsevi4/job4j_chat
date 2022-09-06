create table role
(
    id   serial primary key,
    name varchar(500)
);

create table person
(
    id       serial primary key,
    login    varchar(50),
    password varchar(20),
    role_id  int references role (id)
);

create table room
(
    id   serial primary key,
    name varchar(500)
);

create table message
(
    id      serial primary key,
    text    text,
    person_id int references person (id)
);

create table room_person
(
    id        serial primary key,
    room_id   int references room (id),
    person_id int references person (id)
);

insert into role (name)
values ('ADMIN');

