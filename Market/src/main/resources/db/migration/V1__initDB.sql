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


DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  id	              INT(11) NOT NULL AUTO_INCREMENT,
  title               VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id	              INT(11) NOT NULL AUTO_INCREMENT,
  category_id         INT(11) NOT NULL,
  vendor_code         VARCHAR(8) NOT NULL,
  image               VARCHAR(255),
  title               VARCHAR(255) NOT NULL,
  short_description   VARCHAR(1000) NOT NULL,
  full_description    VARCHAR(5000) NOT NULL,
  price               DECIMAL(8,2) NOT NULL,
  create_at           DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id)
  REFERENCES categories (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders_statuses;

CREATE TABLE orders_statuses (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
  id	      INT(11) NOT NULL AUTO_INCREMENT,
  user_id     INT(11) NOT NULL,
  status_id   INT(11) NOT NULL,
  address VARCHAR(150) NOT NULL ,
  create_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  CONSTRAINT FK_USER_ID FOREIGN KEY (user_id)
  REFERENCES users (id),
  CONSTRAINT FK_STATUS_ID FOREIGN KEY (status_id)
  REFERENCES orders_statuses (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders_item;

CREATE TABLE orders_item (
  id	      INT(11) NOT NULL AUTO_INCREMENT,
  product_id  INT(11) NOT NULL,
  order_id    INT(11) NOT NULL,
  quantity    INT NOT NULL,
  total_price DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
  REFERENCES orders (id),
  CONSTRAINT FK_PRODUCT_ID FOREIGN KEY (product_id)
  REFERENCES products (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO categories (title)
VALUES
("TV"), ("Notebook"), ("Phone");

INSERT INTO products (category_id, vendor_code, image, title, short_description, full_description, price)
VALUES
(1, "00000001", "null", "Nice tv", "Коротко: Хороший телевизор Samsung 40", "LED телевизор Samsung 40", 26000.00),
(1, "00000002", "null", "48\" Телевизор Samsung UE48NU7170U", "Коротко: Хороший телевизор Samsung 48", "LED телевизор Samsung 48", 32000.00),
(1, "00000003", "null", "56\" Телевизор Samsung UE56NU7170U", "Коротко: Хороший телевизор Samsung 56", "LED телевизор Samsung 56", 44000.00);


INSERT INTO orders_statuses (`id`, `name`)
VALUES
('1', 'New'),
('2', 'Packing'),
('3', 'Delivering'),
('4', 'Received'),
('5', 'Returned'),
('6', 'Problem');