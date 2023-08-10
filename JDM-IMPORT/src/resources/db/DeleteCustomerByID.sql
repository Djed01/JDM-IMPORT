DELIMITER //

CREATE PROCEDURE DeleteCustomerByID(IN customerID INT)
BEGIN
    -- Declare variables to store individual and company IDs
    DECLARE individualID INT;
    DECLARE companyID INT;
    DECLARE registerID INT;

    -- Find the individual and company IDs associated with the customer ID
    SELECT CUSTOMER_idCUSTOMER INTO individualID
    FROM Individual
    WHERE CUSTOMER_idCUSTOMER = customerID;

    SELECT CUSTOMER_idCUSTOMER INTO companyID
    FROM Company
    WHERE CUSTOMER_idCUSTOMER = customerID;

    SELECT CUSTOMER_idCUSTOMER INTO registerID
    FROM Register
    WHERE CUSTOMER_idCUSTOMER = customerID;

    -- Use transactions for data consistency
    START TRANSACTION;
    
    -- Delete related records from register table first
    IF registerID IS NOT NULL THEN
        DELETE FROM Register
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;

    -- Delete related records from Individual and Company tables
    IF individualID IS NOT NULL THEN
        DELETE FROM Individual
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;

    IF companyID IS NOT NULL THEN
        DELETE FROM Company
        WHERE CUSTOMER_idCUSTOMER = customerID;
    END IF;

    -- Finally, delete the record from the Customer table
    DELETE FROM Customer
    WHERE idCUSTOMER = customerID;

    -- Commit the transaction
    COMMIT;
END;
//

DELIMITER ;
