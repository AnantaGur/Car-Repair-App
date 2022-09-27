BEGIN TRANSACTION;

DROP TABLE IF EXISTS service_request;

CREATE TABLE service_request (
	request_id serial NOT NULL,
	vehicle_id int NOT NULL,
	service_name varchar(100),
	phone_number char(10) NOT NULL,
	full_name varchar(35) NOT NULL,
	email varchar(30) NOT NULL,
	status varchar (20) NOT NULL,
	CONSTRAINT PK_service_request PRIMARY KEY (request_id),
	CONSTRAINT FK_vehicle_table FOREIGN KEY (vehicle_id) REFERENCES vehicle_table(vehicle_id)
);

COMMIT TRANSACTION;