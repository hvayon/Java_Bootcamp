drop table if exists product;

create table product (
  id bigint primary key,
  name varchar(20),
  price decimal(10,2)
);