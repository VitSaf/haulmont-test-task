CREATE TABLE client (
    client_id INTEGER IDENTITY PRIMARY KEY,
    full_name varchar(50) not null,
    phone_number varchar(12),
    email varchar(50),
    passport_number varchar(50),
);

INSERT INTO client (full_name, phone_number, email, passport_number) VALUES('Vitaliy Safonov', '88005552525', 'safonov@mail.com', '3223 35435345');