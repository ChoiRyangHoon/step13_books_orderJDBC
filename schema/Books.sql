CREATE USER BOOKS IDENTIFIED BY BOOKS;
GRANT CONNECT, RESOURCE, CREATE VIEW, CREATE SEQUENCE TO BOOKS;

--USER TABLE
CREATE TABLE USERLIST(
USER_ID VARCHAR2(30) PRIMARY KEY,
USER_PWD VARCHAR2(30) NOT NULL, 
USER_NAME VARCHAR2(30),   
REG_DATE DATE,  
GRADE VARCHAR2(9) DEFAULT '회원'   
);
 
INSERT INTO USERLIST VALUES ('K',1111,'김현민',SYSDATE,'관리자'); 
INSERT INTO USERLIST VALUES ('C','1111','최량훈',SYSDATE, DEFAULT);
INSERT INTO USERLIST VALUES ('B','1111','박기랑',SYSDATE, DEFAULT);
INSERT INTO USERLIST VALUES ('E','1111','이경은',SYSDATE, DEFAULT);
INSERT INTO USERLIST VALUES ('E2','1111','이창균',SYSDATE, DEFAULT);

COMMIT;

--BOOKS TABLE
CREATE TABLE BOOKS(
BOOKS_ID VARCHAR2(20) PRIMARY KEY,
BOOKS_NAME VARCHAR2(50) NOT NULL,
BOOKS_PRICE NUMBER(12),
STOCK NUMBER(3)
);

INSERT INTO BOOKS VALUES('A01','어린왕자',7000,10);
INSERT INTO BOOKS VALUES('A02','가시고기',6000,10);
INSERT INTO BOOKS VALUES('A03','메이플스토리',7000,10);
INSERT INTO BOOKS VALUES('A04','이것이자바다',7000,10);

SELECT*FROM BOOKS;

COMMIT;

--ORDERS TABLE
CREATE TABLE ORDERS(
ORDER_ID NUMBER(5) PRIMARY KEY,
ORDER_DATE DATE NOT NULL,
USER_ID VARCHAR2(30)NOT NULL REFERENCES USERLIST(USER_ID) ,
ADDRESS VARCHAR2(100) NOT NULL,
TOTAL_AMOUNT NUMBER(20) NOT NULL
);

DROP SEQUENCE ORDER_ID_SEQ;
CREATE SEQUENCE ORDER_ID_SEQ NOCACHE;

--ORDER_LINE TABLE
create table order_line(
  order_line_id number(5) primary key,
  order_id number(5) not null references orders(order_id),
  goods_id varchar2(20) not null references goods(goods_id),
  unit_price number(12) not null,
  qty number(3) not null,
  amount number(10) not null
);

--ORDER_LINE TABLE
CREATE TABLE ORDER_LINE(
ORDER_LINE_ID NUMBER(5) PRIMARY KEY,
ORDER_ID NUMBER(5) NOT NULL REFERENCES ORDERS(ORDER_ID),
BOOKS_ID VARCHAR2(20) NOT NULL REFERENCES BOOKS(BOOKS_ID),
UNIT_PRICE NUMBER(12) NOT NULL,
QTY NUMBER(3) NOT NULL,
AMOUNT NUMBER(10) NOT NULL
);

CREATE SEQUENCE ORDER_LINE_ID_SEQ  NOCACHE;

--REGISTER BOOK TABLE
CREATE TABLE REGBOOK(
REG_NO NUMBER(3) PRIMARY KEY,
REG_NAME VARCHAR2(50) NOT NULL,
REG_WRITER VARCHAR2(30) NOT NULL,
REG_PUBLISHER VARCHAR2(30) NOT NULL,
REG_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE REG_NO_SEQ  NOCACHE;



SELECT*FROM REGBOOK;
SELECT*FROM USERLIST;
SELECT*FROM BOOKS;
SELECT*FROM ORDERS;
SELECT*FROM ORDER_LINE;

COMMIT;
rollback;











