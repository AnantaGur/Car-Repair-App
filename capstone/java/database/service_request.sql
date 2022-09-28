BEGIN TRANSACTION;

DROP TABLE IF EXISTS service_request;

CREATE TABLE service_request (
    vehicle_make varchar(15) NOT NULL,
    vehicle_model varchar(15) NOT NULL,
    vehicle_color varchar(15),
    vehicle_year int NOT NULL,
	service_name varchar(100),
	full_name varchar(35) NOT NULL,
	phone_number char(10) NOT NULL,
	email varchar(30) NOT NULL,
	request_id serial NOT NULL,
	user_id int NOT NULL,
	request_status varchar (30) NOT NULL DEFAULT ('Pending Technician Review'),
	CONSTRAINT PK_service_request PRIMARY KEY (request_id),
	CONSTRAINT FK_service_request FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;