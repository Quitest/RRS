create table if not exists rooms(
	id integer,
	class_of_accommodations VARCHAR(15),
	check_in timestamp,
	check_out timestamp,
	guest_id integer
);

create table if not exists guests(
	id integer,
	first_name varchar(15),
	second_name varchar(15),
	last_name varchar(20),
	age integer
);

insert into rooms values
	(101, 'standart',null,null,null),
	(102, 'standart',null,null,null),
	(201, 'comfort',null,null,null),
	(202, 'comfort',null,null,null),
	(301, 'suite',null,null,null),
	(302, 'suite',null,null,null);

