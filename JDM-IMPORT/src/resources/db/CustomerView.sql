CREATE VIEW CustomerView AS
SELECT idCustomer, FirstName, LastName, CompanyName, Email, Phone
FROM (customer c LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer
LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer);