-- drop table categories cascade constraints;
-- drop table orders cascade constraints;
-- drop table orders_item cascade constraints;
-- drop table orders_statuses cascade constraints;
-- drop table products cascade constraints;
-- drop table roles cascade constraints;
-- drop table users cascade constraints;
-- drop table users_roles cascade constraints;

create table categories (
id number(19,0) generated as identity,
title varchar2(255 char),
primary key (id)
);

create table orders (
id number(19,0) generated as identity,
address varchar2(255 char),
create_at date,
user_id number(19,0),
status_id number(10,0),
primary key (id)
);

create table orders_item (
id number(19,0) generated as identity,
quantity number(10,0),
total_price double precision,
order_id number(19,0),
product_id number(19,0),
primary key (id)
);

create table orders_statuses (
id number(10,0) generated as identity,
name varchar2(255 char),
primary key (id)
);

create table products (
id number(19,0) generated as identity,
create_at date,
full_description long,
image varchar2(255 char),
price double precision,
short_description varchar2(1000 char),
title varchar2(50 char),
vendor_code varchar2(255 char),
category_id number(19,0),
primary key (id)
);

create table roles (
id number(19,0) generated as identity,
role_name varchar2(255 char),
primary key (id)
);

INSERT INTO roles( role_name)
VALUES
('ROLE_CUSTOMER'),
('ROLE_MANAGER'),
('ROLE_ADMIN');

create table users (
id number(19,0) generated as identity,
email varchar2(255 char),
enabled number(1,0),
first_name varchar2(50 char),
last_name varchar2(50 char),
password varchar2(255 char),
phone varchar2(255 char),
username varchar2(255 char),
primary key (id)
);

create table users_roles (
user_id number(19,0) not null,
role_id number(19,0) not null
);

alter table orders add constraint order_user_fk foreign key (user_id) references users;
alter table orders add constraint order_status_fk foreign key (status_id) references orders_statuses;
alter table orders_item add constraint orders_item_order_fk foreign key (order_id) references orders;
alter table orders_item add constraint orders_item_product_fk foreign key (product_id) references products;
alter table products add constraint product_category_fk foreign key (category_id) references categories;
alter table users_roles add constraint users_roles_role_fk foreign key (role_id) references roles;
alter table users_roles add constraint users_roles_user_fk foreign key (user_id) references users;