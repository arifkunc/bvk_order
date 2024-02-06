-- structure
create table orders (
    id varchar(6) not null,
	product_id varchar(6) not null,
    product_name varchar(255) not null,
    quantity int not null,
	price double,
	total_price double,
	primary key(id)
);

create sequence "seq_order_id"
minvalue 1
maxvalue 999999
increment by 1
start with 1
nocache
nocycle;