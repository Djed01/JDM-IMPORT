DELIMITER //

CREATE PROCEDURE GetCustomerOrderDetails()
BEGIN

SELECT idCUSTOMER, FirstName, LastName, CompanyName, Brand, Model, Year, Quantity, TotalPrice, Date, DeliveryDate
FROM customer c
LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer
LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer
INNER JOIN `jdm-import`.order o ON c.idCustomer = o.CUSTOMER_idCUSTOMER
INNER JOIN orderable_car_has_order oco ON c.idCustomer = oco.ORDER_CUSTOMER_idCUSTOMER
INNER JOIN orderable_car oc ON oco.ORDERABLE_CAR_idCar = oc.idCar;

END //

DELIMITER ;