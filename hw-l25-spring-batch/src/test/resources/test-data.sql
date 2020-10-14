insert into airports(id, airport_name, city) values (1, 'Heathrow Airport', 'London');
insert into airports(id, airport_name, city) values (2, 'Paris Charles de Gaulle Airport', 'Paris');
insert into airports(id, airport_name, city) values (3, 'Frankfurt am Main Airport', 'Frankfurt');
insert into airports(id, airport_name, city) values (4, 'Sheremetyevo International Airport', 'Moscow');

insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (1, '1555dee3-b186-4806-823d-e6fcba1a4a35', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (2, 'dc08fd88-72ae-4dfa-a0e3-b3b56bfd2478', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (3, 'dbb9f38d-d615-42a3-a1ff-b2dfd4746164', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (4, 'e831704e-d0e9-48d7-bec4-cb546cc85162', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (5, '71087e0b-b5cd-40f7-9435-4d6ea4872939', 1, 2);

insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (6, 'd3200067-f5a9-47be-96d0-22c94a37fb72', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (7, 'c2040dbb-dbd2-4700-a5b4-b767e1000bca', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (8, '73f27bac-026c-4e46-aa96-dfb74e532e5a', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (9, '7ed8ee61-33df-4e91-b399-10868404e1fe', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (10, '2e7c840c-db5b-44dd-9298-23fae25f0430', 3, 4);

insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (11, '78ab5248-9dde-4bec-9b7c-53f2e4d86a1e', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (12, '159a422e-6fac-455f-b125-c78a7893e43e', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (13, '0c57543c-e8ca-49ad-a85c-27bb7157f12b', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (14, '93c5fc3c-3d2a-4a30-a45d-59c69a8638bd', 1, 2);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (15, 'd6373d86-1ba0-4b01-98e1-8e5e555757cd', 1, 2);

insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (16, '226b9e33-28f4-4603-a0f6-ead2e54c3857', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (17, '9a865109-2351-41b7-9a67-7e0fa94c5706', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (18, '2c5315da-8ce8-4ba2-bee1-663450f4c1cb', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (19, '6db9ad8b-0721-41c5-8241-c1e06a185df7', 3, 4);
insert into flights(id, flight_no, departure_airport_id, arrival_airport_id) values (20, '1800d95e-b8b7-4ca3-ae2e-e1e508bbc59a', 3, 4);

insert into tickets(id, ticket_no, passenger_name, flight_id) values (1, '326b6cc3-ec59-48af-9d5c-bb1b7a31119d', 'Dexter Campbell', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (2, 'f8ae40a7-2f64-4200-868b-289cc036d06b', 'Kian Harvey', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (3, 'b6f191ab-672f-434c-9caa-c696323bbfe5', 'Ellis Campbell', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (4, '743280a6-24a8-4034-be37-c9d33ad8f015', 'Bailey Houghton', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (5, 'be063c00-3d99-4b01-a90a-65f031519898', 'Zak Day', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (6, '975024e1-4ac3-42b1-aa96-73af48107226', 'Jase French', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (7, '1188146a-6ad7-4e7d-8d0b-fa8aa718bc9e', 'Lennox Mooney', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (8, 'afac8d90-518a-489e-8b8c-6a07a4b21f2f', 'Eli Ramirez', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (9, 'b10f77ce-9aa3-471d-a16b-dbdce2f8408a', 'Jonathan Quinn', 1);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (10, '191aa5ee-519d-4f7a-a4be-e3b3a7fbbb01', 'Callen Dotson', 1);

insert into tickets(id, ticket_no, passenger_name, flight_id) values (11, 'b79b8794-5f50-4177-9c10-103540278153', 'Chloe Wallace', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (12, 'df403b78-f52b-405a-8744-8f2382d876df', 'Matilda Berry', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (13, '35ed566b-e68c-4f33-b9e9-cf04b0e15ca3', 'Mollie Hunter', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (14, 'cbf7882d-84b2-45ba-8403-3da5faebc09d', 'Caitlin Young', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (15, '75b1405c-2d27-4e16-b69e-aa4160f72324', 'Aimee Ward', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (16, 'fc67760e-0214-480d-9ea4-6ffd64511b12', 'Cynthia Estrada', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (17, 'cae02275-b90d-4c30-9585-1dfbff73638b', 'Annalise Cannon', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (18, '9e01fa18-83eb-48f3-85b7-291cf3bf031c', 'Aubrielle Wells', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (19, '04bf54d1-c3ac-448a-9006-ffd7014143eb', 'Jazlynn Thompson', 2);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (20, '0c9c7cd8-2daf-4277-a0b0-fd8a70af024e', 'Jamie Lamb', 2);

insert into tickets(id, ticket_no, passenger_name, flight_id) values (21, '84072dc0-a764-4e56-83c8-f60cd17dd46b', 'Rory Reynolds', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (22, 'cc6c74cd-68f6-4ab7-b79b-9cf95c45d060', 'Aubrey Watson', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (23, 'a6e04ac0-d82e-4209-b319-35d41fba0772', 'Brynn Anderson', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (24, 'e12c9a60-9eb3-4cae-821e-54672cdb7212', 'Justice Martin', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (25, 'c36dfa8f-4407-4b08-b024-5d279eade71e', 'Alexis Howard', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (26, '88a45409-7222-4616-ad6c-4723b66c6ba0', 'Glenn England', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (27, 'd4f968d3-dc2f-498d-b8ba-025e263bea73', 'Shay French', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (28, 'b74613dd-cee8-4f9b-abca-37508ccb04dd', 'Sam Riley', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (29, '46896762-bb53-4159-be62-09d37b1b5077', 'Jackie Barlow', 3);
insert into tickets(id, ticket_no, passenger_name, flight_id) values (30, '66ad458e-248e-4428-897f-fee2f222abe1', 'Quinn Coleman', 3);
