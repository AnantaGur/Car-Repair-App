BEGIN TRANSACTION;

DROP TABLE IF EXISTS vehicle_table;

CREATE TABLE vehicle_table (
	vehicle_id serial NOT NULL,
	user_id int NOT NULL,
	make varchar(15) NOT NULL,
	model varchar(15) NOT NULL,
	year int NOT NULL,
	color varchar(15),
	CONSTRAINT PK_vehicle_table PRIMARY KEY (vehicle_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

COMMIT TRANSACTION;