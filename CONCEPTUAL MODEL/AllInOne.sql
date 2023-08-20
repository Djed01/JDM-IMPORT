-- Triger za racunanje ukupne cijene narudzbe --

DELIMITER $$
CREATE TRIGGER calculateTotalPrice
BEFORE INSERT ON orderable_car_has_order
FOR EACH ROW
BEGIN
    DECLARE carPrice DECIMAL(10, 2);

    SELECT Price INTO carPrice FROM orderable_car WHERE idCar = NEW.ORDERABLE_CAR_idCar;

    SET NEW.totalPrice = NEW.Quantity * carPrice;
END
$$
DELIMITER ;


-- Procedura za update automobila u BP --

DELIMITER $$
CREATE PROCEDURE car_update(
    IN p_id INT,
    IN p_brand VARCHAR(100),
    IN p_model VARCHAR(100),
    IN p_year YEAR,
    IN p_price DECIMAL(10, 2),
    IN p_imageURL VARCHAR(255)
)

BEGIN
    UPDATE orderable_car
    SET
        Brand = p_brand,
        Model = p_model,
        Year = p_year,
        Price = p_price,
        ImageURL = p_imageURL
    WHERE
        idCar = p_id;
END $$
DELIMITER ;

-- Triger za upis trenutnog datuma prilikom kreiranja narudzbe --

DELIMITER $$
CREATE TRIGGER insertCurrentDate
BEFORE INSERT ON `order`
FOR EACH ROW
BEGIN
    SET NEW.Date = NOW();
END
$$
DELIMITER ;

-- Pogled za pregled kupaca --
CREATE VIEW CustomerView AS
SELECT idCustomer, FirstName, LastName, CompanyName, Email, Phone
FROM (customer c LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer
LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer);

-- Procedura za brisanje kupca --

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

-- Procedura za brisanje narudzbe --
DELIMITER $$
CREATE PROCEDURE DeleteOrder(IN target_id INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
    END;

    START TRANSACTION;

    DELETE FROM orderable_car_has_order WHERE ORDER_idOrder = target_id;
    DELETE FROM `order` WHERE idOrder = target_id;

    COMMIT;
END
$$
DELIMITER ;


-- Pogled za pregled narucioca --
CREATE VIEW CustomerOrderDetailsView AS
SELECT idOrder,idCUSTOMER, FirstName, LastName, CompanyName, Email, Phone,idCar, Brand, Model, Year,Price,ImageURL,Date, DeliveryDate, Quantity, TotalPrice
FROM customer c
LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer
LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer
INNER JOIN `jdm-import`.order o ON c.idCustomer = o.CUSTOMER_idCUSTOMER
INNER JOIN orderable_car_has_order oco ON o.idOrder = oco.ORDER_idOrder
INNER JOIN orderable_car oc ON oco.ORDERABLE_CAR_idCar = oc.idCar;

-- Procedura za dodavanje narucioca --
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


-- Procedura za unos narudzbe --
DELIMITER $$
CREATE PROCEDURE InsertOrder(
    IN p_DeliveryDate DATE,
    IN p_idCustomer INT,
    IN p_idCar INT,
    IN p_Quantity INT,
    OUT p_generatedID INT
)
BEGIN
    DECLARE v_lastInsertedID INT;


    INSERT INTO `order` (DeliveryDate, CUSTOMER_idCUSTOMER)
    VALUES (p_DeliveryDate, p_idCustomer);


    SET v_lastInsertedID = LAST_INSERT_ID();


    INSERT INTO orderable_car_has_order (ORDERABLE_CAR_idCar, ORDER_idOrder, Quantity)
    VALUES (p_idCar, v_lastInsertedID, p_Quantity);

    SET p_generatedID = v_lastInsertedID;
END
$$
DELIMITER ;

-- Procedura za unos zapisa o registraciji korisnika --
DELIMITER $$
CREATE PROCEDURE `InsertRegisterRecord`(
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
$$
DELIMITER ;

-- Procedura za update narucioca --
DELIMITER $$
CREATE PROCEDURE UpdateCustomer(
    IN customerID INT,
    IN newPhone VARCHAR(20),
    IN newEmail VARCHAR(100),
    IN newFirstName VARCHAR(50),
    IN newLastName VARCHAR(50),
    IN newCompanyName VARCHAR(100)
)
BEGIN
    UPDATE Customer
    SET Phone = newPhone,
        Email = newEmail
    WHERE idCUSTOMER = customerID;


    UPDATE Individual
    SET FirstName = newFirstName,
        LastName = newLastName
    WHERE CUSTOMER_idCUSTOMER = customerID;


    UPDATE Company
    SET CompanyName = newCompanyName
    WHERE CUSTOMER_idCUSTOMER = customerID;
END $$
DELIMITER ;


-- Procedura za update narudzbe --
DELIMITER $$
CREATE PROCEDURE UpdateOrder(
    IN orderID INT,
    IN deliveryDate VARCHAR(20),
    IN customerID INT,
    IN carID INT,
    IN quantity INT
)

BEGIN
    UPDATE `order`
    SET DeliveryDate = deliveryDate,
        CUSTOMER_idCUSTOMER = customerID
    WHERE idOrder = orderID;
	
    UPDATE orderable_car_has_order
    SET Quantity = quantity,
    ORDERABLE_CAR_idCar = carID
    WHERE ORDER_idOrder = orderID;
    
END $$
DELIMITER ;

-- Dodavanje admin naloga --
INSERT INTO employee (FirstName,LastName,Email,Phone,Address,City,PostCode,Username, Password) VALUES ('admin', 'admin','admin@mail.com','066-123-456','Adresa','Grad','78000','admin','admin');









