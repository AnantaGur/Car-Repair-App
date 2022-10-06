insert into users
(username, password_hash, role)
VALUES
--username: fran, password: 123
('fran', '$2a$10$sK06AA0Ln.Mz7kWPYHavPeenEUN9jmhVR9QiZJjYPS3jI2q1fuXjW', 'ROLE_ADMIN');

insert into users
(username, password_hash, role)
--username: gary, password: 1234
VALUES
('GARY', '$2a$10$kCcVff5m/CLUS3Yp1uffTejFUrS1fbnDwXAS.YFfC0A/pIrSjY7ou', 'ROLE_EMPLOYEE');


insert into users
--username: newuser, password 12345
(username, password_hash, role)
VALUES
('Ananta', '$2a$10$L48qsnx0ghnGS0VMr7OCu.59KUXWka53CEespckCvCDT8Z33KWnlC', 'ROLE_USER');

--username: newuser2, password 123456
insert into users
(username, password_hash, role)
VALUES
('Mitchell', '$2a$10$i6LJ9xv4NWoyqDX0fl4ZHuPhJ08wCnoGBrR/BtWLPLdkenlr3gm0i', 'ROLE_USER');

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('ford', 'mustang', 'red', 1990, 'Oil Change', 'Ananta', '1234567890', 'johndoe@gmail.com', 5);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('toyota', 'tacoma', 'black', 2022, 'Brake Change', 'Mitchell', '1234567890', 'te@gmail.com', 6);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('nissan', 'maxima', 'white', 2016, 'Tire Change', 'Ananta', '1234567890', 'nissan@gmail.com', 5);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('honda', 'civic', 'purple', 2014, 'Tire Change', 'Mitchell', '1234567890', 'nissan@gmail.com', 6);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('nissan', 'xterra', 'grey', 2010, 'Battery Change', 'Gary', '1234567890', 'nissan@gmail.com', 5);

--insert into repair_estimate
--(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
--VALUES
--(1, 24.95, 30.00, 1.00, '2022-09-28', '11:00:00');
--
----insert into repair_estimate
----(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
----VALUES
----(3, 34.95, 50.00, 2.00, '2022-09-28', '03:00:00');
----
----insert into repair_estimate
----(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
----VALUES
----(5, 500.00, 150.00, 5.00, '2022-09-28', '09:00:00');
--
