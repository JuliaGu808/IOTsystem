create table holders
(
id int not null auto_increment primary key,
name varchar(30) not null unique
);

create table devices
(
id int not null auto_increment primary key,
name varchar(30) not null,
holdersId int not null,
FOREIGN KEY (holdersId) REFERENCES holders(id),
UNIQUE KEY holder_device (name,holdersId)
);

create table records
(
id int not null auto_increment primary key,
temperature varchar(6) not null,
humidity varchar(6) not null,
recordtime varchar(30) not null,
devicesId int not null,
FOREIGN KEY (devicesId) REFERENCES devices(id)
);

