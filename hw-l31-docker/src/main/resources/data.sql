insert into genres(id, name) values (1, 'Computers & Technology');
insert into genres(id, name) values (2, 'Psychology & Mental Health');

insert into authors(id, name, surname) values (1, 'Philip', 'Pratt');
insert into authors(id, name, surname) values (2, 'Michael', 'Learn');
insert into authors(id, name, surname) values (3, 'James', 'Clear');

insert into books (id, title, author_id, genre_id) values (1, 'A Guide to SQL', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'Concepts of Database Management', 1, 1);
insert into books (id, title, author_id, genre_id) values (3, 'SQL Programming and Coding', 2, 1);
insert into books (id, title, author_id, genre_id) values (4, 'Atomic Habits', 3, 2);

alter sequence genres_id_seq restart with 3;
alter sequence authors_id_seq restart with 4;
alter sequence books_id_seq restart with 5;
