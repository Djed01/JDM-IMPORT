DELIMITER //
CREATE PROCEDURE UpdateOrder(
    IN orderID INT,
    IN deliveryDate VARCHAR(20),
    IN customerID INT,
    IN carID INT,
    IN quantity INT
)

BEGIN
    -- Update the Customer table
    UPDATE `order`
    SET DeliveryDate = deliveryDate,
        CUSTOMER_idCUSTOMER = customerID
    WHERE idOrder = orderID;
	
    UPDATE orderable_car_has_order
    SET Quantity = quantity,
    ORDERABLE_CAR_idCar = carID
    WHERE ORDER_idOrder = orderID;
    
END //
DELIMITER ;
