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
