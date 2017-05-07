CREATE DATABASE cmm;

CREATE SEQUENCE manufacturer_id_seq;

CREATE TABLE car_manufacturers(
		id int PRIMARY KEY DEFAULT NEXTVAL('manufacturer_id_seq'),
		manufacturer_name VARCHAR(30) NOT NULL
);

CREATE SEQUENCE type_id_seq;

CREATE TABLE car_types(
		id int PRIMARY KEY DEFAULT NEXTVAL('type_id_seq'),
		type_name VARCHAR(30) NOT NULL,
		manufacturer_id int REFERENCES car_manufacturers(id)		
);

CREATE SEQUENCE engine_type_id_seq;

CREATE TABLE engine_types(
		id int PRIMARY KEY DEFAULT NEXTVAL('engine_type_id_seq'),
		engine_type VARCHAR(30)
);

CREATE SEQUENCE transmission_type_id_seq;

CREATE TABLE transmission_types(
		id int PRIMARY KEY DEFAULT NEXTVAL('transmission_type_id_seq'),
		transmission_type VARCHAR(30)
);


CREATE SEQUENCE colour_id_seq;

CREATE TABLE colours(
		id int PRIMARY KEY DEFAULT NEXTVAL('colour_id_seq'),
		colour VARCHAR(30)
);

CREATE SEQUENCE picture_id_seq;

CREATE SEQUENCE role_type_id_seq;

CREATE TABLE roles(
		id int PRIMARY KEY DEFAULT NEXTVAL('role_type_id_seq'),
		role_name VARCHAR(30)
);

CREATE SEQUENCE user_id_seq;

CREATE TABLE users(
		id int PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
		first_name VARCHAR(30) NOT NULL,
		last_name VARCHAR(30) NOT NULL,
		email VARCHAR(50) NOT NULL,
		phone_number VARCHAR(30) NOT NULL,
		role_id int REFERENCES roles(id)
);

CREATE SEQUENCE car_id_seq;

CREATE TABLE cars(
		id int PRIMARY KEY DEFAULT NEXTVAL('car_id_seq'),
		seller_id int REFERENCES users(id),
		manufacturer_id int REFERENCES car_manufacturers(id),
		type_id int REFERENCES car_types(id),
		price int NOT NULL,
		mileage int NOT NULL,
		registration_year int NOT NULL,
		extras VARCHAR(1000),
		engine_type_id int REFERENCES engine_types(id),
		transmission_type_id int REFERENCES transmission_types(id),
		colour_id int REFERENCES colours(id),		
		location_longitude DECIMAL,
		location_latitude DECIMAL,
		matriculation_status boolean		
);

CREATE TABLE car_pictures(
		id int PRIMARY KEY DEFAULT NEXTVAL('picture_id_seq'),
		picture_src VARCHAR(150) NOT NULL,
		car_id int REFERENCES cars(id)
);

-- CREATE SEQUENCE bookmark_id_seq;
--
-- CREATE TABLE bookmarks(
--     id INT PRIMARY KEY DEFAULT NEXTVAL('bookmark_id_seq'),
--     car_id int REFERENCES cars(id),
--     user_id int REFERENCES users(id);
-- )


-- CREATE SEQUENCE car_seller_relation_id_seq;

-- CREATE TABLE car_seller_relation(
-- 		id int PRIMARY KEY DEFAULT NEXTVAL('car_seller_relation_id_seq'),
-- 		car_id int REFERENCES cars(id),
-- 		seller_id int REFERENCES users(id)
-- );

INSERT INTO car_manufacturers(manufacturer_name) values ('Audi'),
                                                        ('BMW'),
                                                        ('Citroen'),
                                                        ('Dacia'),
                                                        ('Fiat'),
                                                        ('Ford'),
                                                        ('Honda'),
                                                        ('Hyundai'),
                                                        ('Kia'),
                                                        ('Land Rover'),
                                                        ('Mazda'),
                                                        ('Mercedes-Benz'),
                                                        ('Mitsubishi'),
                                                        ('Nissan'),
                                                        ('Opel'),
                                                        ('Peugeot'),
                                                        ('Renault'),
                                                        ('Seat'),
                                                        ('Skoda'),
                                                        ('Toyota'),
                                                        ('Volkswagen'),
                                                        ('Volvo');



INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('A3', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi')),
	('A4', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi')),
	('A5', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi')),
	('A6', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi')),
	('A7', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi')),
	('A8', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Audi'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('320', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('330', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('335', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('420', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('520', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('525', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW')),
	('530', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'BMW'));

INSERT INTO colours(colour) values('Black'),
                                  ('Blue'),
                                  ('Green'),
                                  ('Grey'),
                                  ('Orange'),
                                  ('Purple'),
                                  ('Red'),
                                  ('Silver'),
                                  ('White'),
                                  ('Yellow');

INSERT INTO engine_types(engine_type) values('PETROL');
INSERT INTO transmission_types(transmission_type) values('MANUAL');
INSERT INTO roles(role_name) VALUES('ADMIN'), ('BUYER'), ('SELLER');
INSERT INTO users(first_name, last_name, email, phone_number, role_id) VALUES('Pista', 'Reszeges',
                  'pussy_destroyer_69_4u@yahoo.com', '0740 000 007', 3);

INSERT INTO cars(manufacturer_id, seller_id, type_id, price, mileage,
				registration_year, extras, engine_type_id,
				transmission_type_id, colour_id, matriculation_status) values(1,1,1,5000,123456, 2005,
				 'ceva', 1,	1, 3, TRUE);

INSERT INTO car_pictures(picture_src, car_id) VALUES('1493835101567_egoista.jpg', 1);

-- SELECT manufacturer_name, type_name FROM car_manufacturers JOIN car_types ON car_manufacturers.id = car_types.manufacturer_id;

Select manufacturer_name, type_name FROM car_manufacturers JOIN car_types ON car_types.manufacturer_id = car_manufacturers.id;


