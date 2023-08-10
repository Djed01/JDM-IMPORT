DELIMITER //
CREATE PROCEDURE InsertOrder(
    IN p_DeliveryDate DATE,
    IN p_idCustomer INT,
    IN p_idCar INT,
    IN p_Quantity INT,
    OUT p_generatedID INT
)
BEGIN
    DECLARE v_lastInsertedID INT;

    -- Insert into the order table
    INSERT INTO `order` (DeliveryDate, CUSTOMER_idCUSTOMER)
    VALUES (p_DeliveryDate, p_idCustomer);

    -- Get the last inserted ID
    SET v_lastInsertedID = LAST_INSERT_ID();

    -- Insert into the orderable_car_has_order table
    INSERT INTO orderable_car_has_order (ORDERABLE_CAR_idCar, ORDER_idOrder, Quantity)
    VALUES (p_idCar, v_lastInsertedID, p_Quantity);

    -- Set the output parameter with the generated ID
    SET p_generatedID = v_lastInsertedID;
END;
//
DELIMITER ;
