insert into genres(id, name) values (1, 'Computers & Technology');
insert into genres(id, name) values (2, 'Psychology & Mental Health');

insert into authors(id, name, surname) values (1, 'Michael', 'Learn');
insert into authors(id, name, surname) values (2, 'Philip', 'Pratt');
insert into authors(id, name, surname) values (3, 'James', 'Clear');

insert into books (id, title, author_id, genre_id) values (1, 'SQL Programming and Coding', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'A Guide to SQL', 2, 1);
insert into books (id, title, author_id, genre_id) values (3, 'Concepts of Database Management', 2, 1);
insert into books (id, title, author_id, genre_id) values (4, 'Atomic Habits', 3, 2);
