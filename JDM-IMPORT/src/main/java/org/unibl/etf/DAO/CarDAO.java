package org.unibl.etf.DAO;

import org.unibl.etf.MODELS.Car;

import java.sql.SQLException;
import java.util.List;

public interface CarDAO {
    List<Car> findAll()throws SQLException;
    Car insert(Car car)throws SQLException;
    boolean update(Car c)throws SQLException;
    boolean delete(Car c)throws SQLException;
}
