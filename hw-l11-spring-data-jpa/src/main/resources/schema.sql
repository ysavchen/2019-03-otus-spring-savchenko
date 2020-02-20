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

drop table if exists comments;
create table comments (
    id bigint primary key auto_increment,
    content varchar(255) not null,
    book_id bigint
);

drop table if exists genres;
create table genres (
    id bigint primary key auto_increment,
    name varchar(255) unique not null
);

alter table books
add foreign key (author_id) references authors(id);

alter table books
add foreign key (genre_id) references genres(id);

alter table comments
add foreign key (book_id) references books(id);