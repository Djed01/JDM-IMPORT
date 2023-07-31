package org.unibl.etf.DAO.impl;

import org.unibl.etf.DAO.OrderDAO;
import org.unibl.etf.MODELS.Car;
import org.unibl.etf.MODELS.Order;
import org.unibl.etf.UTIL.ConnectionPool;
import org.unibl.etf.UTIL.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
//    @Override
//    public List<Order> findAll()throws SQLException{
//        String query = "select * from vjezba_view";
//
//        var connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        List<Order> results = new ArrayList<>();
//        try {
//            connection = connectionPool.checkOut();
//            statement = connection.prepareStatement(query);
//            resultSet = statement.executeQuery();
//            while (resultSet.next())
//                results.add(new Order());
//        }finally {
//            connectionPool.checkIn(connection);
//            DBUtil.close(statement, resultSet);
//        }
//        return results;
//    }
//
//    @Override
//    public Order insert(Order order)throws SQLException{
//        String query="{call vjezba_insert(?,?,?,?,?,?)}";
//
//        var connectionPool = ConnectionPool.getInstance();
//        Connection connection = null;
//        CallableStatement statement=null;
//        try {
//            connection=connectionPool.checkOut();
//            statement=connection.prepareCall(query);
//            statement.setString(1,order.getBrand());
//            statement.setString(2, order.getModel());
//            statement.setString(3, order.getYear());
//            statement.setDouble(4, order.getPrice());
//            statement.setString(5,order.getImageURL());
//
//            statement.registerOutParameter(6, Types.INTEGER);
//            statement.executeUpdate();
//            var id=statement.getInt(0);
//            order.setId(id);
//        }
//        finally {
//            connectionPool.checkIn(connection);
//            DBUtil.close(statement);
//        }
//        return order;
//    }
}
