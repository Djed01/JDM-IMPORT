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
        String query = "select * from CustomerOrderDetailsView";

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


    @Override
    public Order insert(Order order)throws SQLException{
        String query="{call InsertOrder(?,?,?,?,?)}";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            statement.setString(1,order.getDeliveryDate());
            statement.setInt(2,order.getCustomer().getId());
            statement.setInt(3,order.getCar().getId());
            statement.setInt(4,order.getQuantity());
            statement.setNull(5, Types.INTEGER);

            statement.registerOutParameter(5, Types.INTEGER);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert the customer record.");
            }

            // Retrieve the generated ID
            int generatedId = statement.getInt(5);
            order.setId(generatedId);
        }finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return order;
    }

    @Override
    public boolean delete(int id)throws SQLException{
        String query = "{call DeleteOrder(?)}";
        boolean status=false;

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareStatement(query);
            statement.setInt(1,id);
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return status;
    }


    @Override
    public Order update(Order order)throws SQLException{
        String query = "{call UpdateOrder(?,?,?,?,?)}";
        boolean status=false;

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareStatement(query);
            statement.setInt(1,order.getId());
            statement.setString(2,order.getDeliveryDate());
            statement.setInt(3,order.getCustomer().getId());
            statement.setInt(4,order.getCar().getId());
            statement.setInt(5,order.getQuantity());
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return order;
    }

}
