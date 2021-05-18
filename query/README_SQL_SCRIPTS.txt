Create iotfake table:

CREATE TABLE `iotfake` (
  `id` int NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(45) NOT NULL,
  `deviceHolder` varchar(30) NOT NULL,
  `temperature` varchar(5) DEFAULT NULL,
  `humidity` varchar(5) DEFAULT NULL,
  `timestamp` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




Script to insert into iotfake
INSERT INTO `iot20`.`iotfake` (`deviceName`, `deviceHolder`, `temperature`, `humidity`, `timestamp`) VALUES ('esp32', 'Johnny', '24', '18', '1621325580');


