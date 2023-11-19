/* 
CREATE TABLE IF NOT EXISTS USERS (
  userid INT PRIMARY KEY auto_increment,
  username VARCHAR(20) UNIQUE,
  salt VARCHAR,
  password VARCHAR,
  firstname VARCHAR(20),	
  lastname VARCHAR(20)
);
*/

INSERT INTO USERS VALUES(null, 'user1', '1', 'FKRs0pbdF75gFi7hCnGVrg==', 'user', '1');
INSERT INTO USERS VALUES(null, 'user2', '1', 'FKRs0pbdF75gFi7hCnGVrg==', 'user', '2');
INSERT INTO USERS VALUES(null, 'user3', '1', 'FKRs0pbdF75gFi7hCnGVrg==', 'user', '3');

/*
CREATE TABLE IF NOT EXISTS NOTES (
    id INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)
);
*/

INSERT INTO NOTES VALUES(null, 'Note1', 'Note Description 1', '1');
INSERT INTO NOTES VALUES(null, 'Note2', 'Note Description 2', '1');
INSERT INTO NOTES VALUES(null, 'Note3', 'Note Description 3', '1');
INSERT INTO NOTES VALUES(null, 'Note4', 'Note Description 4', '1');
INSERT INTO NOTES VALUES(null, 'Note5', 'Note Description 5', '1');

/*
CREATE TABLE IF NOT EXISTS CREDENTIALS (
    id INT PRIMARY KEY auto_increment,
    url VARCHAR(100),
    username VARCHAR (30),
    key VARCHAR,
    password VARCHAR,
    userid INT,
    foreign key (userid) references USERS(userid)
);
*/

/*
INSERT INTO CREDENTIALS VALUES(null, 'URL 1', 'User1', 'GQFjNaTK+bUMKMV2JNqTJQ==', 'FKRs0pbdF75gFi7hCnGVrg==', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 2', 'User1', 'GQFjNaTK+bUMKMV2JNqTJQ==',  'FKRs0pbdF75gFi7hCnGVrg==', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 3', 'User1', 'GQFjNaTK+bUMKMV2JNqTJQ==',  'FKRs0pbdF75gFi7hCnGVrg==', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 4', 'User1', 'GQFjNaTK+bUMKMV2JNqTJQ==',  'FKRs0pbdF75gFi7hCnGVrg==', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 5', 'User1', 'GQFjNaTK+bUMKMV2JNqTJQ==',  'FKRs0pbdF75gFi7hCnGVrg==', 1);
*/
