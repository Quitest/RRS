DROP TABLE IF EXISTS rooms;
DROP TABLE IF EXISTS guests;
DROP TABLE IF EXISTS reserves;

create table if not exists rooms(
	id INT AUTO_INCREMENT  PRIMARY KEY,
	class_of_accommodations VARCHAR(15)
);

create table if not exists guests(
	id INT AUTO_INCREMENT  PRIMARY KEY,
	lastname varchar(15),
	middle_name varchar(15),
	name varchar(20),
	age integer
);

CREATE TABLE IF NOT EXISTS reserves(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    check_in TIMESTAMP WITH TIME ZONE,
    check_out TIMESTAMP WITH TIME ZONE,
    guest_id INT,
    room_id INT,
    foreign key (guest_id) references guests(id),
    foreign key (room_id) references rooms(id)
);

insert into rooms values
	(101, 'standart'),
	(102, 'standart'),
	(201, 'comfort'),
	(202, 'comfort'),
	(301, 'suite'),
	(302, 'suite');

INSERT INTO guests (lastname,middle_name,name,age) values
('Фамилия1','Отчество1','Имя1',1),
('Фамилия2','Отчество2','Имя2',2),
('Фамилия3','Отчество3','Имя3',3),
('Фамилия4','Отчество4','Имя4',4);
