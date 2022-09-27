BEGIN TRANSACTION;

DROP TABLE IF EXISTS repair_estimate;

CREATE TABLE repair_estimate (
	repair_id serial NOT NULL,
	request_id int NOT NULL,
	part_cost decimal NOT NULL,
	labor_cost decimal NOT NULL,
	total_time decimal NOT NULL,
	pick_up_date DATE NOT NULL,
	pick_up_time TIME NOT NULL,
	status varchar (15),
	CONSTRAINT PK_repair_id PRIMARY KEY (repair_id),
	CONSTRAINT FK_service_request FOREIGN KEY (request_id) REFERENCES service_request(request_id)
);

COMMIT TRANSACTION;