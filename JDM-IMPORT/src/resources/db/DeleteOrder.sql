DELIMITER //
CREATE PROCEDURE DeleteOrder(IN target_id INT)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        -- Error handling if needed
        ROLLBACK;
    END;

    START TRANSACTION;

    DELETE FROM orderable_car_has_order WHERE ORDER_idOrder = target_id;
    DELETE FROM `order` WHERE idOrder = target_id;

    COMMIT;
END;
//
DELIMITER ;
