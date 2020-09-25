drop database if exists InfyRail;
create database InfyRail;
use InfyRail;
create table routes(
	id int auto_increment primary key,
	source varchar(20),
	destination varchar(20)
);

create table trains(
	id bigint auto_increment primary key,
	train_name varchar(30),
	arrival_time varchar(20),
	departure_time varchar(20),
	fare double(7,2),
	route_id int, 
	FOREIGN KEY (route_id) REFERENCES routes(id)
);

insert into routes(id,source,destination) values(100,'Bangalore','Mangalore');
insert into trains(id,train_name,arrival_time,departure_time,fare,route_id) values(1000,'Karwar Express','08:30:00 AM','08:55:00 PM',1200,100);
insert into trains(id,train_name,arrival_time,departure_time,fare,route_id) values(1001,'Kannur Express','08:30:00 AM','08:55:00 PM',1200,100);
select * from routes;
select * from trains;

