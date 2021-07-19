DROP DATABASE IF EXISTS amandigioielli;
CREATE DATABASE amandigioielli;
USE amandigioielli;

create table if not exists admin
(
	admin_id int auto_increment
		primary key,
	email varchar(16) not null,
	password varchar(16) not null
);

create table if not exists category
(
	category_id int auto_increment
		primary key,
	name varchar(50) not null,
	description varchar(250) null,
	constraint name
		unique (name)
);

create table if not exists product
(
	product_id int auto_increment
		primary key,
	product_name varchar(30) not null,
	description varchar(500) null,
	short_description varchar(250) null,
	quantity int default 0 null,
	price double default 0 null,
	tax_rate double default 22 null,
	discount double default 0 null,
	is_active tinyint(1) default 1 null,
	url varchar(500) null,
	constraint product_name
		unique (product_name)
);

create table if not exists image
(
	image_id varchar(100) not null
		primary key,
	product_id int not null,
	url varchar(500) null,
	alt_text varchar(50) null,
	height int null,
	width int null,
	constraint image_ibfk_1
		foreign key (product_id) references product (product_id)
			on update cascade on delete cascade
);

create index product_id
	on image (product_id);

create table if not exists product_categories
(
	category_id int not null,
	product_id int not null,
	constraint product_categories_ibfk_1
		foreign key (category_id) references category (category_id)
			on update cascade on delete cascade,
	constraint product_categories_ibfk_2
		foreign key (product_id) references product (product_id)
			on update cascade on delete cascade
);

create index category_id
	on product_categories (category_id);

create index product_id
	on product_categories (product_id);

create table if not exists user
(
	user_id int auto_increment
		primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	email varchar(50) not null,
	password varchar(64) not null,
	phone varchar(50) null,
	newsletter tinyint(1) default 0 null,
	is_active tinyint(1) default 1 null,
	constraint email
		unique (email)
);

create table if not exists address
(
	address_number int auto_increment,
	user_id int not null,
	street_address varchar(100) not null,
	city varchar(50) not null,
	zip varchar(5) not null,
	province varchar(2) not null,
	phone varchar(50) not null,
	info varchar(50) null,
	primary key (address_number, user_id),
	constraint address_ibfk_1
		foreign key (user_id) references user (user_id)
			on update cascade on delete cascade
);

create index user_id
	on address (user_id);

create table if not exists orders
(
	order_id int
		primary key,
	user_id int not null,
	destination varchar(100) not null,
	total_discount double null,
	total_price double not null,
	n_products int not null,
	data DATE NOT NULL,
	status varchar(50) default 'Ordine Effettuato',
	constraint orders_ibfk_1
		foreign key (user_id) references user (user_id)
			on update CASCADE
);

create table if not exists order_details
(
	order_details_id int auto_increment
		primary key,
	order_id int not null,
	product_id int not null,
	p_name varchar(30) not null,
	p_quantity int not null,
	p_price double not null,
	p_tax_rate double not null,
	p_discount double not null,
	constraint order_details_ibfk_1
		foreign key (order_id) references orders (order_id)
			on update cascade on delete cascade
);

create index order_id
	on order_details (order_id);

create index user_id
	on orders (user_id);

create table if not exists review
(
	review_id int auto_increment
		primary key,
	user_id int not null,
	product_id int not null,
	description varchar(1000) null,
	score double not null,
	review_date date not null,
	constraint review_ibfk_1
		foreign key (user_id) references user (user_id)
			on update cascade,
	constraint review_ibfk_2
		foreign key (product_id) references product (product_id)
			on update cascade on delete cascade
);

create index product_id
	on review (product_id);

create index user_id
	on review (user_id);

create table if not exists wishlist
(
	wishlist_id int not null
		primary key,
	user_id int not null,
	constraint user_id
		unique (user_id),
	constraint wishlist_ibfk_1
		foreign key (user_id) references user (user_id)
			on update cascade on delete cascade
);

create table if not exists wishlist_item
(
	wishlist_id int not null,
	product_id int not null,
	quantity int not null,
	primary key (wishlist_id, product_id),
	constraint product_id
		unique (product_id),
	constraint wishlist_id
		unique (wishlist_id),
	constraint wishlist_item_ibfk_1
		foreign key (wishlist_id) references wishlist (wishlist_id)
			on update cascade on delete cascade
);

INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate) VALUES ("Orecchini Ginkgo","Orecchini realizzati a mano in Argento Sterling 925 rodiato","",3,150, 22);
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate) VALUES ("Anello Ginkgo","Anello realizzato a mano in Argento Sterling 925 dorato","",2,80, 22);
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate) VALUES ("Orecchini Sunflowers","Orecchini realizzati a mano in Argento Sterling 925 rosato","",1,99, 22);