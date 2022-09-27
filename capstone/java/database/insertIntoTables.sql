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

insert into vehicle_table
(user_id, make, model, year, color)
values
(3,'chevy', 'mustang', 1990, 'white');

insert into vehicle_table
(user_id, make, model, year, color)
values
(4,'ford', 'mustang', 1990, 'white');

insert into vehicle_table
(user_id, make, model, year, color)
values
(5,'toyota', 'mustang', 1995, 'black');

insert into vehicle_table
(user_id, make, model, year, color)
values
(3,'chevy', 'mustang', 1990, 'white');

insert into vehicle_table
(user_id, make, model, year, color)
values
(5,'nissan', 'altima', 1999, 'white');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(1, 'oil change', '1234567890', 'john doe', 'johndoe@alwkgnaws.aovdn' , 'completed');

insert into repair_estimate
(request_id, part_cost, labor_cost, total_time, pick_up_date, pick_up_time, status)
values
(1, 24.95, 50.00, 2.00, '2022-09-27', '14:25:00', 'pending');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(1, 'tire change', '1234567890', 'jane doe', 'janendoe@alwkgnaws.aovdn' , 'pending');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(2, 'wiper fluid change', '1234567890', 'hello there', 'hi@alwkgnaws.aovdn' , 'completed');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(3, 'battery change', '1234567890', 'good bye', 'adios@alwkgnaws.aovdn' , 'pending');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(4, 'motor change', '1234567890', 'get a car', 'car@alwkgnaws.aovdn' , 'completed');

insert into service_request
(vehicle_id, service_name, phone_number, full_name, email, status)
VALUES
(5, 'transmission change', '1234567890', 'hellen', 'hellen@alwkgnaws.aovdn' , 'pending');

insert into repair_estimate
(request_id, part_cost, labor_cost, total_time, pick_up_date, pick_up_time, status)
values
(2, 50.95, 20.00, 4.00, '2022-09-27', '14:00:00', 'pending');

insert into repair_estimate
(request_id, part_cost, labor_cost, total_time, pick_up_date, pick_up_time, status)
values
(3, 75.25, 80.00, 5.00, '2022-09-27', '16:30:00', 'pending');

insert into repair_estimate
(request_id, part_cost, labor_cost, total_time, pick_up_date, pick_up_time, status)
values
(4, 100.00, 150.00, 6.00, '2022-09-27', '17:45:00', 'pending');

insert into repair_estimate
(request_id, part_cost, labor_cost, total_time, pick_up_date, pick_up_time, status)
values
(5, 1500.00, 550.00, 10.00, '2022-09-27', '13:30:00', 'pending');




