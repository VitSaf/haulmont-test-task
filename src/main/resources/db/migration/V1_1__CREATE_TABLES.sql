CREATE TABLE client (
	client_id integer PRIMARY KEY IDENTITY,
	full_name varchar(50),
	phone_number varchar(50),
	email varchar(50),
	passport_number varchar(50),
	bank_id integer,
	offer_id integer
);

CREATE TABLE credit (
	credit_id integer PRIMARY KEY IDENTITY,
	credit_limit decimal,
	rate decimal,
	bank_id integer,
	offer_id integer
);

CREATE TABLE credit_offer (
	offer_id integer PRIMARY KEY IDENTITY,
	client_id integer,
	credit_id integer,
	credit_full_sum decimal,
	duration_in_months integer,
	payment_id integer
);

CREATE TABLE payments (
	payment_id integer PRIMARY KEY IDENTITY,
	offer_id integer,
	payday date,
	sum_of_payment decimal,
	credit_part_of_payment decimal,
	rate_part_of_payment decimal
);

CREATE TABLE bank (
	bank_id integer PRIMARY KEY IDENTITY,
	bank_name varchar(100),
	client_id integer,
	credit_id integer
);

ALTER TABLE credit_offer ADD CONSTRAINT credit_offer_fk0 FOREIGN KEY (client_id) REFERENCES client(client_id);

ALTER TABLE credit_offer ADD CONSTRAINT credit_offer_fk1 FOREIGN KEY (credit_id) REFERENCES credit(credit_id);

ALTER TABLE credit_offer ADD CONSTRAINT credit_offer_fk2 FOREIGN KEY (payment_id) REFERENCES payments(payment_id);

ALTER TABLE payments ADD CONSTRAINT payments_fk1 FOREIGN KEY (offer_id) REFERENCES credit_offer(offer_id);

ALTER TABLE client ADD CONSTRAINT client_fk0 FOREIGN KEY (bank_id) REFERENCES bank(bank_id);

ALTER TABLE client ADD CONSTRAINT client_fk1 FOREIGN KEY (offer_id) REFERENCES credit_offer(offer_id);

ALTER TABLE credit ADD CONSTRAINT credit_fk0 FOREIGN KEY (bank_id) REFERENCES bank(bank_id);

ALTER TABLE credit ADD CONSTRAINT credit_fk1 FOREIGN KEY (offer_id) REFERENCES credit_offer(offer_id);

ALTER TABLE bank ADD CONSTRAINT bank_fk0 FOREIGN KEY (client_id) REFERENCES client(client_id);

ALTER TABLE bank ADD CONSTRAINT bank_fk1 FOREIGN KEY (credit_id) REFERENCES credit(credit_id);

INSERT INTO bank (bank_name) VALUES ('MAIN OFFICE');
INSERT INTO bank (bank_name) VALUES ('COUNTRY OFFICE');
INSERT INTO bank (bank_name) VALUES ('LOCAL OFFICE');

INSERT INTO client (full_name, phone_number, email, passport_number, bank_id) VALUES('Vitaliy Safonov', '88005552525', 'safonov@mail.com', '3223 35435345',1);
INSERT INTO client (full_name, phone_number, email, passport_number, bank_id) VALUES('Ivanov Ivan', '88005552526', 'ivanov@mail.com', '3223 35435346',1);
INSERT INTO client (full_name, phone_number, email, passport_number, bank_id) VALUES('Petrov Petr', '88005552527', 'petrov@mail.com', '3223 35435347',1);
INSERT INTO client (full_name, phone_number, email, passport_number, bank_id) VALUES('Dmitriev Dmitry', '88005552528', 'dm@mail.com', '3223 35435348',2);


INSERT INTO credit (credit_limit, rate, bank_id) VALUES(100000, 10.9, 1);

INSERT INTO credit_offer (client_id, credit_id, credit_full_sum, duration_in_months) VALUES(2, 0, 110900.4, 6);

UPDATE client SET offer_id = 0 WHERE client_id = 2;
UPDATE credit SET offer_id = 0 WHERE credit_id = 0;

--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-01-15', 18483.4, 16667.7, 1816.67);
--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-02-15',  18483.4, 16667.7, 1816.67);
--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-03-15',  18483.4, 16667.7, 1816.67);
--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-04-15', 18483.4, 16667.7, 1816.67);
--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-05-15',  18483.4, 16667.7, 1816.67);
--INSERT INTO payments (offer_id,payday, sum_of_payment, credit_part_of_payment, rate_part_of_payment) VALUES(0,DATE '2022-06-15',  18483.4, 16667.7, 1816.67);



