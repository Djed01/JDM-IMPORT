package org.unibl.etf.DAO.impl;

import org.unibl.etf.DAO.CustomerDAO;
import org.unibl.etf.MODELS.Car;
import org.unibl.etf.MODELS.Company;
import org.unibl.etf.MODELS.Customer;
import org.unibl.etf.MODELS.Individual;
import org.unibl.etf.UTIL.ConnectionPool;
import org.unibl.etf.UTIL.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> findAll() throws SQLException {
        String query = "select * from vjezba_view";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Customer> results = new ArrayList<>();
        try {
            connection = connectionPool.checkOut();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next())
                if(resultSet.getString(1)!=null) {
                    results.add(new Individual(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(4)));
                }else {
                    results.add(new Company(resultSet.getString(1), resultSet.getString(3),
                            resultSet.getString(4)));
                }
        } finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement, resultSet);
        }
        return results;
    }

    @Override
    public Customer insert(Customer customer)throws SQLException {

        String query="{call vjezba_insert(?,?,?,?,?,?)}";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            if(customer instanceof Company){
                statement.setString(1,((Company) customer).getName());
                statement.setString(2,((Company) customer).getEmail());
                statement.setString(3,((Company) customer).getPhone());

            }else if(customer instanceof Individual){
                statement.setString(1,((Individual) customer).getFirstName());
                statement.setString(2,((Individual) customer).getLastName());
                statement.setString(3,((Individual) customer).getEmail());
                statement.setString(4,((Individual) customer).getPhone());
            }

            statement.registerOutParameter(6, Types.INTEGER);
            statement.executeUpdate();
            var id=statement.getInt(0);
            customer.setId(id);
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        String query="{call vjezba_update(?,?,?,?,?,?)}";
        boolean status=false;
        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            if(customer instanceof Company) {
                statement.setString(1, ((Company) customer).getName());
                statement.setString(3, ((Company) customer).getEmail());
                statement.setString(4, ((Company) customer).getPhone());
            }else if(customer instanceof Individual){
                statement.setString(1,((Individual) customer).getFirstName());
                statement.setString(2,((Individual) customer).getLastName());
                statement.setString(3,((Individual) customer).getEmail());
                statement.setString(4,((Individual) customer).getPhone());
            }
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return status;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException {
        String query = "delete from vjezba where IdVjezbe=?";
        boolean status=false;

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareStatement(query);
            statement.setInt(1,customer.getId());
            status=statement.executeUpdate()==1;
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return status;
    }
}
