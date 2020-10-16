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
  create_at   DATETIME NOT NULL,
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
(1, "00000001", "null", "Jeans", "Коротко: Хороший телевизор Samsung 40", "LED телевизор Samsung 40", 26000.00),
(1, "00000002", "null", "48\" Телевизор Samsung UE48NU7170U", "Коротко: Хороший телевизор Samsung 48", "LED телевизор Samsung 48", 32000.00),
(1, "00000003", "null", "56\" Телевизор Samsung UE56NU7170U", "Коротко: Хороший телевизор Samsung 56", "LED телевизор Samsung 56", 44000.00);


