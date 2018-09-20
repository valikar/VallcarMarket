

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
		role_id int REFERENCES roles(id) NOT NULL,
		password VARCHAR(150) NOT NULL,
		password_validation VARCHAR(150) NOT NULL,
		enabled BOOLEAN
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
		matriculation_status boolean,
		available_status boolean,
		views int
);

CREATE TABLE car_pictures(
		id int PRIMARY KEY DEFAULT NEXTVAL('picture_id_seq'),
		picture_src VARCHAR(150),
		car_id int REFERENCES cars(id)
);

CREATE TABLE bookmarks(
	user_id int REFERENCES users(id) NOT NULL,
	car_id int REFERENCES cars(id) NOT NULL
);

CREATE SEQUENCE conversation_id_seq;

CREATE TABLE conversations(
	id INT PRIMARY KEY DEFAULT NEXTVAL('conversation_id_seq'),
	sender_id int REFERENCES users(id) NOT NULL,
	receiver_id int REFERENCES users(id) NOT NULL,
	title VARCHAR(50) NOT NULL,
	sender_name VARCHAR(50) NOT NULL,
	receiver_name VARCHAR(50) NOT NULL,
	last_message VARCHAR(50) NOT NULL
);

CREATE TABLE messages(
	conversation_id int REFERENCES conversations(id) NOT NULL,
	sender_id int REFERENCES users(id) NOT NULL,
	receiver_id int REFERENCES users(id) NOT NULL,
	message VARCHAR(200) NOT NULL,
	time VARCHAR(50) NOT NULL
);

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

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('C3', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Citroen')),
	('C4', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Citroen')),
	('C5', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Citroen')),
	('DS3', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Citroen')),
	('DS4', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Citroen'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Amarok', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volkswagen')),
	('Golf', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volkswagen')),
	('Scirocco', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volkswagen')),
	('Tiguan', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volkswagen')),
	('Touareg', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volkswagen'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Amarok', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Skoda')),
	('Octavia', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Skoda')),
	('Rapid', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Skoda')),
	('Superb', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Skoda')),
	('Yeti', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Skoda'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Escort', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford')),
	('Explorer', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford')),
	('F150', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford')),
	('Mondei', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford')),
	('Mustang', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford')),
	('Transit', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Ford'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Logan', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Dacia')),
	('Duster', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Dacia')),
	('Sandero', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Dacia'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Civic', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Honda')),
	('Accord', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Honda')),
	('CR-V', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Honda'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Punto', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Fiat')),
	('Bravo', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Fiat')),
	('Stilo', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Fiat'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('RX7', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Mazda')),
	('RX8', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Mazda')),
	('Miata', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Mazda'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('Astra', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Opel')),
	('Vectra', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Opel')),
	('Corsa', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Opel'));

INSERT INTO car_types(type_name, manufacturer_id) VALUES
	('S30', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volvo')),
	('S40', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volvo')),
	('S60', (SELECT id FROM car_manufacturers WHERE manufacturer_name = 'Volvo'));



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

INSERT INTO engine_types(engine_type) values('PETROL'),
                                            ('DIESEL'),
                                            ('HYBRID'),
                                            ('ELECTRIC');
INSERT INTO transmission_types(transmission_type) values('MANUAL'),
                                                        ('AUTOMATIC');
INSERT INTO roles(role_name) VALUES('ADMIN'), ('BUYER'), ('SELLER');

INSERT INTO users(first_name, last_name, email, phone_number, role_id,password,password_validation) VALUES
	('Conor','McGregor','conormcgregor@yahoo.com','0769696969',3,'password','password');
INSERT INTO users(first_name, last_name, email, phone_number, role_id,password,password_validation) VALUES
	('Gigi','Becali','gigibecali@yahoo.com','0769696969',2,'password','password');

INSERT INTO cars(manufacturer_id, seller_id, type_id, price, mileage,
				registration_year, extras, engine_type_id,
				transmission_type_id, colour_id, matriculation_status) values(1,1,1,5000,123456, 2005,
				 'ceva', 1,	1, 3, TRUE);

INSERT INTO car_pictures(picture_src, car_id) VALUES('1493835101567_egoista.jpg', 1);

-- SELECT manufacturer_name, type_name FROM car_manufacturers JOIN car_types ON car_manufacturers.id = car_types.manufacturer_id;

Select manufacturer_name, type_name FROM car_manufacturers JOIN car_types ON car_types.manufacturer_id = car_manufacturers.id;
-- Select c.id, c.seller_id, cm.manufacturer_name, ct.type_name, c.price, c.mileage, c.registration_year, c.extras, et.engine_type, tt.transmission_type, co.colour, c.matriculation_status, cp.picture_src, c.available_status from cars c join car_manufacturers cm on c.manufacturer_id = cm.id join car_types ct on c.type_id = ct.id join engine_types et on c.engine_type_id = et.id join transmission_types tt on c.transmission_type_id = tt.id join colours co on c.colour_id = co.id join car_pictures cp on c.id = cp.car_id

 