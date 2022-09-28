insert into users
(username, password_hash, role)
VALUES
('fran', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDK', 'ROLE_USER');

insert into users
(username, password_hash, role)
VALUES
('GARY', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDz', 'ROLE_USER');

insert into users
(username, password_hash, role)
VALUES
('orange', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDy', 'ROLE_USER');

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('ford', 'mustang', 'red', 1990, 'oil change', 'john doe', '1234567890', 'johndoe@gmail.com', 3);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('toyota', 'tacoma', 'black', 2022, 'engine change', 'tech elevator', '1234567890', 'te@gmail.com', 5);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('nissan', 'maxima', 'white', 2016, 'tire rotation', 'jane doe', '1234567890', 'nissan@gmail.com', 3);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('honda', 'civic', 'purple', 2014, 'transmission rotation', 'daniel commins', '1234567890', 'nissan@gmail.com', 4);

insert into service_request
(vehicle_make, vehicle_model, vehicle_color, vehicle_year, service_name, full_name, phone_number, email, user_id)
VALUES
('nissan', 'xterra', 'grey', 2010, 'wipers change', 'gary moon', '1234567890', 'nissan@gmail.com', 4);

insert into repair_estimate
(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
VALUES
(1, 24.95, 30.00, 1.00, '2022-09-28', '11:00:00');

insert into repair_estimate
(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
VALUES
(2, 34.95, 50.00, 2.00, '2022-09-28', '03:00:00');

insert into repair_estimate
(request_id, parts_cost, labor_cost, total_time, pick_up_date, pick_up_time)
VALUES
(3, 500.00, 150.00, 5.00, '2022-09-28', '09:00:00');