
DELIMITER //

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
END //

DELIMITER ;
