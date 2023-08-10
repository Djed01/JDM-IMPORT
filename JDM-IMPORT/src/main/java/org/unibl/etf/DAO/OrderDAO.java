package org.unibl.etf.DAO;

import org.unibl.etf.MODELS.Customer;
import org.unibl.etf.MODELS.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    List<Order> findAll()throws SQLException;
    Order insert(Order order)throws SQLException;
    boolean delete(int id)throws SQLException;
    Order update(Order order)throws SQLException;
}
