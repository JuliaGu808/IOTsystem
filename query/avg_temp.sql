CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `iot20`@`%` 
    SQL SECURITY DEFINER
VIEW `avg_temp` AS
    SELECT 
        `get_all`.`deviceHolder` AS `deviceHolder`,
        FORMAT(AVG(`get_all`.`temperature`), 2) AS `temperature`
    FROM
        `get_all`
    GROUP BY `get_all`.`deviceHolder`;
    
####################################################################
# UPDATE QUERY
####################################################################

CREATE VIEW `avg_temp` AS
    SELECT 
        `get_all`.`deviceHolder` AS `deviceHolder`,
        `get_all`.`deviceName` AS `deviceName`,
        FORMAT(AVG(`get_all`.`temperature`), 2) AS `temperature`
    FROM
        `get_all`
    GROUP BY `get_all`.`deviceHolder`, `get_all`.`deviceName`
