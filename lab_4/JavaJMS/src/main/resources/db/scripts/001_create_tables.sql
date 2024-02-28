create table coach
(
    id     serial primary key,
    name   varchar not null unique,
    city   varchar not null
);

create table pokemon
(
    id     serial primary key,
    name   varchar not null unique,
    attack int     not null,
    life   int     not null,
    coach_id int references coach(id)
);