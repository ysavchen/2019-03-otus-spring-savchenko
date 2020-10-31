drop table if exists books;
create table books (
    id bigserial primary key,
    title varchar(255) not null,
    author_id bigint,
    genre_id bigint
);

drop table if exists authors;
create table authors (
    id bigserial primary key,
    name varchar(255) not null,
    surname varchar(255) not null
);

drop table if exists genres;
create table genres (
    id bigserial primary key,
    name varchar(255) not null
);

alter table books
add constraint fk_books_authors foreign key (author_id) references authors(id);

alter table books
add constraint fk_books_genres foreign key (genre_id) references genres(id);