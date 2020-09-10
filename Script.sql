create schema coronakitdb;

use coronakitdb;

create table product(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   description VARCHAR(1000) NOT NULL,
   cost VARCHAR(10) NOT NULL,
   PRIMARY KEY ( id )
);

create table user(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(200) NOT NULL,
   email VARCHAR(100) NOT NULL,
   contact VARCHAR(10) NOT NULL,
   address VARCHAR(1000) NOT NULL,
   PRIMARY KEY ( id, contact )
);
