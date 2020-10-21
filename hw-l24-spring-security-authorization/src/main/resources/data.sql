insert into genres(id, name) values (1, 'Computers & Technology');
insert into genres(id, name) values (2, 'Psychology & Mental Health');

insert into authors(id, name, surname) values (1, 'Philip', 'Pratt');
insert into authors(id, name, surname) values (2, 'Michael', 'Learn');
insert into authors(id, name, surname) values (3, 'James', 'Clear');

insert into books (id, title, author_id, genre_id) values (1, 'A Guide to SQL', 1, 1);
insert into books (id, title, author_id, genre_id) values (2, 'Concepts of Database Management', 1, 1);
insert into books (id, title, author_id, genre_id) values (3, 'SQL Programming and Coding', 2, 1);
insert into books (id, title, author_id, genre_id) values (4, 'Atomic Habits', 3, 2);

/* admin@test.com , password */
insert into users (id, first_name, last_name, email, password) values (1, 'name', 'surname', 'admin@test.com', '$2y$10$vQqIxLRb0Q1ePkp5mhTPfeCojXgc1ttQ05LiJUgcjmjphDWYCphee');

/* john.doe@test.com , #Start01 */
insert into users (id, first_name, last_name, email, password) values (2, 'John', 'Doe', 'john.doe@test.com', '$2a$10$gfoYoDKAUG5qO8obPlvPlOe8F08HEszg.H0uVeam9ObC9eTeV46uy');

insert into authorities (id, role) values (1, 'ROLE_ADMIN');
insert into authorities (id, role) values (2, 'ROLE_USER');

insert into user_authorities (user_id, authority_id) values (1, 1);
insert into user_authorities (user_id, authority_id) values (2, 2);
