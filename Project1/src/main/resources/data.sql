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

INSERT INTO USERS VALUES(null, 'user1', '1', 'TzJqDKQshkzk+FGKDpnn6y+UNnBUSnQOQPH2a3RGLPtdpudIHB+QMnMjFFeoXfYwVu2xmmtByy1Tr52qkzYTDt3sUWOpCDzVJlDEy+d/wPYtVUIVLRXUv+1HEqH3Ufichwu/gq+tOVEti3hCuqy8w5Upive0vQUcSrr48FVqD1xtofPOAbuQIDoHaEdpyg246eHbiOd6/japqALyqDne091iz5gG/HCYM5tptqCpF57S/M0PXdITAOL+Oqkb0XruYty9/WiozI1jAYVif2ONv1aySnR6Grg/HjE4WG9a8lVkE52j35DFzDEW2S4jnmZX7OwnACDhYg79PJQRr1jxSg==', 'user', '1');
INSERT INTO USERS VALUES(null, 'user2', '1', 'TzJqDKQshkzk+FGKDpnn6y+UNnBUSnQOQPH2a3RGLPtdpudIHB+QMnMjFFeoXfYwVu2xmmtByy1Tr52qkzYTDt3sUWOpCDzVJlDEy+d/wPYtVUIVLRXUv+1HEqH3Ufichwu/gq+tOVEti3hCuqy8w5Upive0vQUcSrr48FVqD1xtofPOAbuQIDoHaEdpyg246eHbiOd6/japqALyqDne091iz5gG/HCYM5tptqCpF57S/M0PXdITAOL+Oqkb0XruYty9/WiozI1jAYVif2ONv1aySnR6Grg/HjE4WG9a8lVkE52j35DFzDEW2S4jnmZX7OwnACDhYg79PJQRr1jxSg==', 'user', '2');
INSERT INTO USERS VALUES(null, 'user3', '1', 'TzJqDKQshkzk+FGKDpnn6y+UNnBUSnQOQPH2a3RGLPtdpudIHB+QMnMjFFeoXfYwVu2xmmtByy1Tr52qkzYTDt3sUWOpCDzVJlDEy+d/wPYtVUIVLRXUv+1HEqH3Ufichwu/gq+tOVEti3hCuqy8w5Upive0vQUcSrr48FVqD1xtofPOAbuQIDoHaEdpyg246eHbiOd6/japqALyqDne091iz5gG/HCYM5tptqCpF57S/M0PXdITAOL+Oqkb0XruYty9/WiozI1jAYVif2ONv1aySnR6Grg/HjE4WG9a8lVkE52j35DFzDEW2S4jnmZX7OwnACDhYg79PJQRr1jxSg==', 'user', '3');

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
INSERT INTO CREDENTIALS VALUES(null, 'URL 1', 'User1', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 2', 'User1', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=',  'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 3', 'User1', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=',  'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 4', 'User1', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=',  'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 1);
INSERT INTO CREDENTIALS VALUES(null, 'URL 5', 'User1', 'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=',  'U2FsdGVkX1/brUacgpMzzR4rIa2ZFgKVmAxHPe9xl3o=', 1);
*/
