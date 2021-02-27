CREATE TABLE client (
    client_id INTEGER IDENTITY PRIMARY KEY,
    full_name varchar(50) not null,
    phone_number varchar(12),
    email varchar(50),
    passport_number varchar(50),
);