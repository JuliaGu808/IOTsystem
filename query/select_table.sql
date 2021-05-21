insert into holders (name) values ("jack");
insert into holders (name) values ("rose");

select * from holders;

select id from holders where name='jack';

#################################################3

insert into devices (name, holdersId) values ("device_1",3);

select * from devices;

select id from devices where holdersId=1 and name='device_1';

##############################

select * from records;

insert into records (temperature, humidity, recordtime, devicesId) values ('19.05', '30.80', '1621413784', 1);
insert into records (temperature, humidity, recordtime, devicesId) values ('17.00', '40.09', '1621413790', 3);

############################
CREATE VIEW get_all AS
select devices.name as deviceName, holders.name as deviceHolder, temperature, humidity, recordtime from records 
left join devices on devicesId=devices.id 
left join holders on devices.holdersId=holders.id 
order by recordtime desc;

select * from get_all;

ALTER TABLE holders AUTO_INCREMENT = 1;

ALTER TABLE devices AUTO_INCREMENT = 1;

ALTER TABLE records AUTO_INCREMENT = 1;



