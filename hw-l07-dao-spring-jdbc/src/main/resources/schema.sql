DROP TABLE IF EXISTS BOOKS;
CREATE TABLE BOOKS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(255) NOT NULL,
    AUTHOR_ID BIGINT,
    GENRE_ID BIGINT
);

DROP TABLE IF EXISTS AUTHORS;
CREATE TABLE AUTHORS(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    SURNAME VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS GENRES;
CREATE TABLE GENRES(
    ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL
);

ALTER TABLE BOOKS
ADD FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHORS(ID) ON DELETE CASCADE;

ALTER TABLE BOOKS
ADD FOREIGN KEY (GENRE_ID) REFERENCES GENRES(ID) ON DELETE CASCADE;