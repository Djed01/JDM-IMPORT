package org.unibl.etf.DAO;

import org.unibl.etf.MODELS.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll()throws SQLException;
    Customer insert(Customer customer,String type,int idEmployee)throws SQLException;
    boolean delete(int id)throws SQLException;
    boolean update(Customer customer, int id)throws SQLException;
}
