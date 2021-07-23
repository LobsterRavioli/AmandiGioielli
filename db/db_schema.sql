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


INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Orecchini Ginkgo','Orecchini realizzati a mano in Argento Sterling 925 brunito, pietre dure semipreziose, cubic zirconia bianchi','Orecchini in argento brunito, pietre dure.',3, 140.70, 22, '/AmandiGioielli/images/products/orecchini-ginkgo.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Anello Ginkgo','Anello realizzato a mano in Argento Sterling 925 brunito, pietre dure semipreziose, cubic zirconia bianchi','Anello in argento brunito, pietre dure.',5, 44.80, 22, '/AmandiGioielli/images/products/anello-ginkgo.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Bracciale Ginkgo','Bracciale realizzato a mano in Argento Sterling 925 brunito, pietre dure semipreziose, cubic zirconia bianchi','Bracciale in argento brunito, pietre dure.',6, 157.50, 22, '/AmandiGioielli/images/products/bracciale-ginkgo.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Orecchini Caligo Uranus','Orecchini realizzati a mano in Argento Sterling 925 rosato, pietre dure semipreziose, cubic zirconia bianchi, perle di acqua dolce, smalti a freddo dipinti a mano','Orecchini in argento rosato, pietre dure, perle, smalti.',5, 102.20, 22, '/AmandiGioielli/images/products/orecchini-caligo-uranus.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Collana Caligo Uranus','Collana realizzata a mano in Argento Sterling 925 rosato, pietre dure semipreziose, cubic zirconia bianchi, madreperla incisa a mano, smalti a freddo dipinti a mano','Collana in argento rosato, pietre dure, madreperla, smalti.',7, 123.20, 22, '/AmandiGioielli/images/products/collana-caligo-uranus.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Collana Turkish Ornamental','Collana realizzata a mano in Argento Sterling 925 dorato, pietre dure semipreziose, perle di acqua dolce, smalti a freddo dipinti a mano','Collana in argento dorato, pietre dure, perle, smalti.',6, 70, 22, '/AmandiGioielli/images/products/collana-turkish-ornamental.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Orecchini Turkish Ornamental','Orecchini realizzati a mano in Argento Sterling 925 dorato, pietre dure semipreziose, perle di acqua dolce, smalti a freddo dipinti a mano','Orecchini in argento dorato, pietre dure, perle, smalti.',4, 197.75, 22, '/AmandiGioielli/images/products/orecchini-turkish-ornamental.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Anello Tropical Fish','Anello realizzato a mano in Argento Sterling 925 rodiato, pietre dure semipreziose, smalti a freddo dipinti a mano','Anello in argento rodiato, pietre dure, smalti.',6, 51.10, 22, '/AmandiGioielli/images/products/anello-tropical-fish.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Collana Tropical Fish','Collana realizzata a mano in Argento Sterling 925 rodiato, pietre dure semipreziose, perle di acqua dolce, smalti a freddo dipinti a mano','Collana in argento rodiato, pietre dure, perle, smalti.',6, 70.70, 22, '/AmandiGioielli/images/products/collana-tropical-fish.jpg');
INSERT INTO product (product_name,description,short_description,quantity,price,tax_rate, url) VALUES ('Bracciale Tropical Fish','Bracciale realizzato a mano in Argento Sterling 925 rodiato, pietre dure semipreziose, perle di acqua dolce, smalti a freddo dipinti a mano','Bracciale in argento rodiato, pietre dure, perle, smalti.',4, 50.60, 22, '/AmandiGioielli/images/products/bracciale-tropical-fish.jpg');


INSERT INTO category (name, description) VALUES ('Anelli', '');
INSERT INTO category (name, description) VALUES ('Bracciali', '');
INSERT INTO category (name, description) VALUES ('Collane & Pendenti', '');
INSERT INTO category (name, description) VALUES ('Orecchini', '');
INSERT INTO category (name, description) VALUES ('Naturalis Mundi', '');
INSERT INTO category (name, description) VALUES ('The Blue', '');
INSERT INTO category (name, description) VALUES ('Ornamental', '');
INSERT INTO category (name, description) VALUES ('Butterfly Effect', '');


INSERT INTO product_categories (product_id, category_id) VALUES (1,5);
INSERT INTO product_categories (product_id, category_id) VALUES (1,4);
INSERT INTO product_categories (product_id, category_id) VALUES (2,5);
INSERT INTO product_categories (product_id, category_id) VALUES (2,1);
INSERT INTO product_categories (product_id, category_id) VALUES (3,5);
INSERT INTO product_categories (product_id, category_id) VALUES (3,2);
INSERT INTO product_categories (product_id, category_id) VALUES (4,4);
INSERT INTO product_categories (product_id, category_id) VALUES (4,8);
INSERT INTO product_categories (product_id, category_id) VALUES (5,3);
INSERT INTO product_categories (product_id, category_id) VALUES (5,8);
INSERT INTO product_categories (product_id, category_id) VALUES (6,3);
INSERT INTO product_categories (product_id, category_id) VALUES (6,7);
INSERT INTO product_categories (product_id, category_id) VALUES (7,7);
INSERT INTO product_categories (product_id, category_id) VALUES (7,4);
INSERT INTO product_categories (product_id, category_id) VALUES (8,1);
INSERT INTO product_categories (product_id, category_id) VALUES (8,6);
INSERT INTO product_categories (product_id, category_id) VALUES (9,3);
INSERT INTO product_categories (product_id, category_id) VALUES (9,6);
INSERT INTO product_categories (product_id, category_id) VALUES (10,2);
INSERT INTO product_categories (product_id, category_id) VALUES (10,6);