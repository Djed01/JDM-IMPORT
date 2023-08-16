CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertRegisterRecord`(
    IN employeeId INT,
    IN customerId INT
)
BEGIN
    IF EXISTS (SELECT 1 FROM employee WHERE idEMPLOYEE = employeeId) AND
       EXISTS (SELECT 1 FROM customer WHERE idCUSTOMER = customerId) THEN
        INSERT INTO register (EMPLOYEE_idEMPLOYEE, CUSTOMER_idCUSTOMER, Date)
        VALUES (employeeId, customerId, CURRENT_TIMESTAMP);
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid employeeId or customerId';
    END IF;
END