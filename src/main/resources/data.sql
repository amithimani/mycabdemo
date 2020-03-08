/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

--Create Sample Cars
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(1, now(), '123', 5, TRUE, 1, 'GAS', TRUE, 'KIA');
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(2, now(), '456', 4, TRUE, 2, 'PATROL', TRUE, 'BMW');
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(3, now(), '789', 2, TRUE, 3, 'DIESEL', TRUE, 'SUZUKI');
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(4, now(), '234', 6, TRUE, 4, 'HYBRID', TRUE, 'AUDI');
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(5, now(), '345', 1, TRUE, 5, 'GAS', TRUE, 'VW');
insert into car (id, date_created, licence_plate, seat_count, convertible, rating, engine_type, is_active, manufacturer)
values(6, now(), '567', 2, TRUE, 6, 'HYBRID', FALSE, 'HONDA');