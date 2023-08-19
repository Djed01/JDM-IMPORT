DELIMITER $$

CREATE PROCEDURE DeleteCustomerByID(IN customerID INT)
BEGIN

    DECLARE individualID INT;
    DECLARE companyID INT;
    DECLARE registerID INT;


    SELECT CUSTOMER_idCUSTOMER INTO individualID
    FROM Individual
    WHERE CUSTOMER_idCUSTOMER = customerID;

    SELECT CUSTOMER_idCUSTOMER INTO companyID
    FROM Company
    WHERE CUSTOMER_idCUSTOMER = customerID;

    SELECT CUSTOMER_idCUSTOMER INTO registerID
    FROM Register
    WHERE CUSTOMER_idCUSTOMER = customerID;


    START TRANSACTION;


    IF registerID IS NOT NULL THEN
        DELETE FROM Register
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;


    IF individualID IS NOT NULL THEN
        DELETE FROM Individual
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;

    IF companyID IS NOT NULL THEN
        DELETE FROM Company
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;

    DELETE FROM Customer
    WHERE idCUSTOMER = customerID;

    COMMIT;
END
$$

DELIMITER ;
