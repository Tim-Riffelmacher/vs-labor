CREATE DATABASE IF NOT EXISTS category;
CREATE DATABASE IF NOT EXISTS product;
CREATE DATABASE IF NOT EXISTS user;

GRANT ALL PRIVILEGES ON category.* TO 'webshopuser'@'%';

GRANT ALL PRIVILEGES ON product.* TO 'webshopuser'@'%';

GRANT ALL PRIVILEGES ON user.* TO 'webshopuser'@'%';

CREATE TABLE user.role (
	id INT NOT NULL AUTO_INCREMENT,
	level1 INT,
	type VARCHAR(255),
	PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE user.customer (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(255) NOT NULL,
	lastname VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	role INT NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into user.role (`level1`, `type`) values(0, 'admin');
insert into user.role (`level1`, `type`) values(1, 'user');


insert into user.customer (`name`, `lastname`, `password`, `username`, `role`) values('admin', 'admin', 'admin', 'admin', 1);
