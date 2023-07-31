package org.unibl.etf.DAO;

import org.unibl.etf.MODELS.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll()throws SQLException;
    Customer insert(Customer customer)throws SQLException;
    boolean delete(Customer customer)throws SQLException;
    boolean update(Customer customer)throws SQLException;
}
