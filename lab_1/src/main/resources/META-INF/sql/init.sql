DROP  TABLE IF EXISTS users CASCADE;
DROP  TABLE IF EXISTS questions CASCADE;
DROP  TABLE IF EXISTS answers CASCADE;

create table if not exists users(
    id int primary key,
    firstName text not null,
    lastName text not null,
    password int not null,
    email text,
    role text check(role = 'ADMIN' or role = 'USER')
);

create table questions (
    id int primary key,
    txt text NOT NULL
);

create table answers (
    id int primary key,
    id_user int REFERENCES users(id) ON DELETE CASCADE,
    id_question int REFERENCES questions(id) ON DELETE CASCADE,
    answer text
);

