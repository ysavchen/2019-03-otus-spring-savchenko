insert into airports(id, airport_name, city) values (1, 'Sheremetyevo International Airport', 'Moscow');
insert into airports(id, airport_name, city) values (2, 'Pulkovo Airport', 'Saint Petersburg');
insert into airports(id, airport_name, city) values (3, 'Heathrow Airport', 'London');
insert into airports(id, airport_name, city) values (4, 'Frankfurt am Main Airport', 'Frankfurt');

insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (1, '1555dee3-b186-4806-823d-e6fcba1a4a35', 'domestic', 1, 2);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (2, 'dc08fd88-72ae-4dfa-a0e3-b3b56bfd2478', 'international', 1, 3);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (3, 'dbb9f38d-d615-42a3-a1ff-b2dfd4746164', 'international', 1, 4);

insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (4, 'e831704e-d0e9-48d7-bec4-cb546cc85162', 'domestic', 2, 1);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (5, '71087e0b-b5cd-40f7-9435-4d6ea4872939', 'international', 2, 3);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (6, 'd3200067-f5a9-47be-96d0-22c94a37fb72', 'international', 2, 4);

insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (7, 'c2040dbb-dbd2-4700-a5b4-b767e1000bca', 'international', 3, 1);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (8, '73f27bac-026c-4e46-aa96-dfb74e532e5a', 'international', 3, 2);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (9, '7ed8ee61-33df-4e91-b399-10868404e1fe', 'international', 3, 4);

insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (10, '2e7c840c-db5b-44dd-9298-23fae25f0430', 'international', 4, 1);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (11, '78ab5248-9dde-4bec-9b7c-53f2e4d86a1e', 'international', 4, 2);
insert into flights(id, flight_no, type, departure_airport_id, arrival_airport_id) values (12, '159a422e-6fac-455f-b125-c78a7893e43e', 'international', 4, 3);