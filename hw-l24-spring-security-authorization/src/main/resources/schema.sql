drop table if exists books;
create table books (
    id bigint primary key auto_increment,
    title varchar(255) not null,
    author_id bigint,
    genre_id bigint
);

drop table if exists authors;
create table authors (
    id bigint primary key auto_increment,
    name varchar(255) not null,
    surname varchar(255) not null
);

drop table if exists genres;
create table genres (
    id bigint primary key auto_increment,
    name varchar(255) not null
);

drop table if exists users;
create table users (
    id bigint primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);

drop table if exists authorities;
create table authorities (
    id bigint primary key auto_increment,
    role varchar(255) not null
);

drop table if exists user_authorities;
create table user_authorities (
    primary key (user_id, authority_id),
    user_id bigint not null,
    authority_id bigint not null
);

alter table books
add foreign key (author_id) references authors(id);

alter table books
add foreign key (genre_id) references genres(id);

alter table user_authorities
add foreign key (user_id) references users(id);

alter table user_authorities
add foreign key (authority_id) references authorities(id);