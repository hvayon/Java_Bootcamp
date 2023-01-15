drop table if exists chat.user, chat.room, chat.message;
drop schema if exists chat;

create schema chat;

create table chat.user (
id          serial primary key,
login       text unique not null,
password    text not null
);

create table chat.room (
id          serial primary key,
chat_name   text not null,
chat_owner  int not null references chat.user(id)
);

create table chat.message (
id          serial primary key,
room_id     int not null references chat.room(id),
author      int not null references chat.user(id),
message     text not null,
time        timestamp
);