DELIMITER //
CREATE PROCEDURE UpdateCustomer(
    IN customerID INT,
    IN newPhone VARCHAR(20),
    IN newEmail VARCHAR(100),
    IN newFirstName VARCHAR(50),
    IN newLastName VARCHAR(50),
    IN newCompanyName VARCHAR(100)
)
BEGIN
    -- Update the Customer table
    UPDATE Customer
    SET Phone = newPhone,
        Email = newEmail
    WHERE idCUSTOMER = customerID;

    -- Update the Individual table
    UPDATE Individual
    SET FirstName = newFirstName,
        LastName = newLastName
    WHERE CUSTOMER_idCUSTOMER = customerID;

    -- Update the Company table
    UPDATE Company
    SET CompanyName = newCompanyName
    WHERE CUSTOMER_idCUSTOMER = customerID;
END //
DELIMITER ;
