DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
  id int(11) NOT NULL AUTO_INCREMENT,
  role_name varchar(45) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO roles(id, role_name)
VALUES
(1,'ROLE_CUSTOMER'),(2, 'ROLE_MANAGER'),(3, 'ROLE_ADMIN');


DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(100) DEFAULT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  enabled tinyint(1) NOT NULL,
  email varchar(100) DEFAULT NULL,
  phone varchar(15) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

INSERT INTO users (id,username,password,first_name,last_name, enabled, email,phone)
VALUES
(1, 'admin','$2a$10$JjS3Dz6Hdcb9Uxh6/GcjwuJlS0Bx0wNMHzJzP/zTyzySOC5TWBoVq','Admin','Admin', 1,'admin@gmail.com','+79881111111');

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
  user_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (user_id,role_id),
  KEY role_fk_idx (role_id),
  CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);