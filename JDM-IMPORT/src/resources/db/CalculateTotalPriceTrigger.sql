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
