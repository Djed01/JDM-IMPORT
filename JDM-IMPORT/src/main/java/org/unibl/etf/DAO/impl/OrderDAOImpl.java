package org.unibl.etf.DAO.impl;

import org.unibl.etf.DAO.OrderDAO;
import org.unibl.etf.MODELS.*;
import org.unibl.etf.UTIL.ConnectionPool;
import org.unibl.etf.UTIL.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> findAll()throws SQLException{
        String query = "{call GetCustomerOrderDetails()}";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> results = new ArrayList<>();
        try {
            connection = connectionPool.checkOut();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Customer customer;
                if(resultSet.getString(5)==null){
                     customer = new Individual(resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4),
                            resultSet.getString(6), resultSet.getString(7));
                }else{
                    customer = new Company(resultSet.getInt(2), resultSet.getString(5),
                            resultSet.getString(6), resultSet.getString(7));
                }

                Car car = new Car(resultSet.getInt(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),
                        resultSet.getDouble(12),resultSet.getString(13));

                results.add(new Order(resultSet.getInt(1),customer,car,resultSet.getString(14),resultSet.getString(15),resultSet.getInt(16),
                        resultSet.getDouble(17)));
            }
        }finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement, resultSet);
        }
        return results;
    }


}
