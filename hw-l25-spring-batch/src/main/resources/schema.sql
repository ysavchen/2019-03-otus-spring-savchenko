drop table if exists airports;
create table airports (
    id bigint primary key auto_increment,
    airport_name varchar(255) not null,
    city varchar(255)
);

drop table if exists flights;
create table flights (
    id bigint primary key auto_increment,
    flight_no varchar(255) not null,
    departure_airport_id bigint,
    arrival_airport_id bigint
);

drop table if exists tickets;
create table tickets (
    id bigint primary key auto_increment,
    ticket_no varchar(255) not null,
    passenger_name varchar(255),
    flight_id bigint
);

alter table tickets
add foreign key (flight_id) references flights(id);

alter table flights
add foreign key (departure_airport_id) references airports(id);

alter table flights
add foreign key (departure_airport_id) references airports(id);