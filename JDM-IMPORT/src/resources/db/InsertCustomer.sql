DELIMITER $$

CREATE PROCEDURE InsertCustomer(
    IN phone VARCHAR(20),
    IN email VARCHAR(50),
    IN type VARCHAR(10),
    IN name VARCHAR(50),
    IN surname VARCHAR(50),
    IN company_name VARCHAR(100),
    OUT customer_id INT
)
BEGIN
    INSERT INTO customer (Phone, Email) VALUES (phone, email);
    SET customer_id = LAST_INSERT_ID();


    IF type = 'Individual' THEN
        INSERT INTO individual (CUSTOMER_idCUSTOMER, FirstName, LastName) VALUES (customer_id, name, surname);
    ELSEIF type = 'Company' THEN
        INSERT INTO company (CUSTOMER_idCUSTOMER, CompanyName) VALUES (customer_id, company_name);
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid customer type';
    END IF;
END
$$

DELIMITER ;
