CREATE TABLE pizza (id SERIAL PRIMARY KEY, pizza_type_id INTEGER, name VARCHAR(50),  price REAL);
CREATE TABLE orders (id SERIAL PRIMARY KEY, user_id INTEGER, state VARCHAR(50), price REAL, discount REAL, creation_date timestamp, release_date timestamp);
CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(50), login VARCHAR(50), password VARCHAR(60), address_id INTEGER, customer_card_id INTEGER, enabled smallint default 1, register_date timestamp, last_visit_date timestamp);
CREATE TABLE roles (id SERIAL PRIMARY KEY, name VARCHAR(50) );
CREATE TABLE users_roles (user_id INTEGER, role_id INTEGER);
CREATE TABLE order_pizza (order_id INTEGER, pizza_id INTEGER,  price REAL, items INTEGER);
CREATE TABLE customer_card (id SERIAL PRIMARY KEY, user_id INTEGER, total_sum REAL);
CREATE TABLE address (id SERIAL PRIMARY KEY, country VARCHAR(50), city VARCHAR(50), street VARCHAR(50), house VARCHAR(50), room VARCHAR(50));
CREATE TABLE country (id SERIAL PRIMARY KEY, name VARCHAR(50) );
CREATE TABLE pizza_type (id SERIAL PRIMARY KEY, name VARCHAR(50), description TEXT );


INSERT INTO pizza (name, pizza_type_id, price) VALUES ('pizza1', 1, 1.0);
INSERT INTO pizza (name, pizza_type_id, price) VALUES ('pizza2', 2, 2.0);
INSERT INTO pizza (name, pizza_type_id, price) VALUES ('pizza3', 3, 3.0);
INSERT INTO pizza (name, pizza_type_id, price) VALUES ('pizza4', 1, 4.0);


INSERT INTO pizza_type (name, description) VALUES ('SEA', 'Pizza with seafood');
INSERT INTO pizza_type (name, description) VALUES ('CHEESE', 'Pizza with big cheese');
INSERT INTO pizza_type (name, description) VALUES ('FRESH', 'Pizza with fresh fruits');
