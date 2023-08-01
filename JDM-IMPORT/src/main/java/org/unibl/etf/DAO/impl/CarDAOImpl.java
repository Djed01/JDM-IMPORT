package org.unibl.etf.DAO.impl;

import org.unibl.etf.DAO.CarDAO;
import org.unibl.etf.MODELS.Car;
import org.unibl.etf.UTIL.ConnectionPool;
import org.unibl.etf.UTIL.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    @Override
    public List<Car> findAll() throws SQLException {
        String query = "select * from orderable_car";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Car> results = new ArrayList<>();
        try {
            connection = connectionPool.checkOut();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next())
                results.add(new Car(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getDouble(5), resultSet.getString(6)));
        } finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement, resultSet);
        }
        return results;
    }

    @Override
    public Car insert(Car car)throws SQLException {

        String query="INSERT INTO orderable_car(Brand, Model, Year, Price, ImageURL) VALUES (?, ?, ?, ?, ?)";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            statement.setString(1,car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getYear());
            statement.setDouble(4, car.getPrice());
            statement.setString(5,car.getImageURL());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert the car record.");
            }

            // Retrieve the generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    car.setId(id);
                } else {
                    throw new SQLException("Failed to retrieve the generated ID for the car record.");
                }
            }
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return car;
    }

    @Override
    public boolean update(Car car) throws SQLException {
        String query="{call car_update(?,?,?,?,?,?)}";
        boolean status=false;
        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            statement.setInt(1, car.getId());
            statement.setString(2,car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getYear());
            statement.setDouble(5, car.getPrice());
            statement.setString(6,car.getImageURL());
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return status;
    }

    @Override
    public boolean delete(Car car) throws SQLException {
        String query = "delete from orderable_car where IdCar=?";
        boolean status=false;

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareStatement(query);
            statement.setInt(1,car.getId());
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return status;
    }

}
