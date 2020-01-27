insert into books (id, title) values (1, 'SQL Programming and Coding');
insert into books (id, title) values (2, 'A Guide to SQL');
insert into books (id, title) values (3, 'Atomic Habits');
insert into books (id, title) values (4, 'How to Talk to Anyone');

insert into authors(id, name, surname) values (1, 'Michael', 'Learn');
insert into authors(id, name, surname) values (2, 'Philip', 'Pratt');
insert into authors(id, name, surname) values (3, 'James', 'Clear');
insert into authors(id, name, surname) values (4, 'Leil', 'Lowndes');

insert into genres(id, name) values (1, 'Computers & Technology');
insert into genres(id, name) values (2, 'Psychology & Mental Health');

update books
set author_id = 1, genre_id = 1
where id = 1;

update books
set author_id = 2, genre_id = 1
where id = 2;

update books
set author_id = 3, genre_id = 2
where id = 3;

update books
set author_id = 4, genre_id = 2
where id = 4;

update authors
set book_id = 1
where id = 1;

update authors
set book_id = 2
where id = 2;

update authors
set book_id = 3
where id = 3;

update authors
set book_id = 4
where id = 4;