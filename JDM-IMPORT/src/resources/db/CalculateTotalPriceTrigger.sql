DELIMITER //
CREATE TRIGGER calculateTotalPrice
BEFORE INSERT ON orderable_car_has_order
FOR EACH ROW
BEGIN
    DECLARE carPrice DECIMAL(10, 2);
    
    -- Get the car price based on idCar from the car table
    SELECT Price INTO carPrice FROM orderable_car WHERE idCar = NEW.ORDERABLE_CAR_idCar;
    
    -- Calculate totalPrice
    SET NEW.totalPrice = NEW.Quantity * carPrice;
END;
//
DELIMITER ;
