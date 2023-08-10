CREATE VIEW CustomerOrderDetailsView AS
SELECT idOrder,idCUSTOMER, FirstName, LastName, CompanyName, Email, Phone,idCar, Brand, Model, Year,Price,ImageURL,Date, DeliveryDate, Quantity, TotalPrice
FROM customer c
LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer
LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer
INNER JOIN `jdm-import`.order o ON c.idCustomer = o.CUSTOMER_idCUSTOMER
INNER JOIN orderable_car_has_order oco ON o.idOrder = oco.ORDER_idOrder
INNER JOIN orderable_car oc ON oco.ORDERABLE_CAR_idCar = oc.idCar;

