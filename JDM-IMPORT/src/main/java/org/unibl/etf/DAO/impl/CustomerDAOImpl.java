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
        String query = "SELECT idCustomer, FirstName, LastName, CompanyName, Email, Phone\n" +
                "FROM (customer c\n" +
                "LEFT JOIN individual i ON c.idCustomer = i.CUSTOMER_idCustomer\n" +
                "LEFT JOIN company co ON c.idCustomer = co.CUSTOMER_idCustomer);";

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
                if(resultSet.getString(4)==null) {
                    results.add(new Individual(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getString(5), resultSet.getString(6)));
                }else {
                    results.add(new Company(resultSet.getInt(1), resultSet.getString(4),
                            resultSet.getString(5), resultSet.getString(6)));
                }
        } finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement, resultSet);
        }
        return results;
    }

    @Override
    public Customer insert(Customer customer,String type)throws SQLException {

        String query="{call InsertCustomer(?,?,?,?,?,?,?)}";

        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            if(customer instanceof Company){

                statement.setString(1,((Company) customer).getPhone());
                statement.setString(2,((Company) customer).getEmail());
                statement.setString(3,type);
                statement.setNull(4, Types.VARCHAR);
                statement.setNull(5, Types.VARCHAR);
                statement.setString(6,((Company) customer).getName());

            }else if(customer instanceof Individual){

                statement.setString(1,((Individual) customer).getPhone());
                statement.setString(2,((Individual) customer).getEmail());
                statement.setString(3,type);
                statement.setString(4,((Individual) customer).getFirstName());
                statement.setString(5,((Individual) customer).getLastName());
                statement.setNull(6, Types.VARCHAR);

            }

            statement.registerOutParameter(7, Types.INTEGER);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert the customer record.");
            }

            // Retrieve the generated ID
            int generatedId = statement.getInt(7);
            customer.setId(generatedId);
        }
        finally {
            connectionPool.checkIn(connection);
            DBUtil.close(statement);
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer, int id) throws SQLException {
        String query="{call UpdateCustomer(?,?,?,?,?,?)}";
        boolean status=false;
        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;
        try {
            connection=connectionPool.checkOut();
            statement=connection.prepareCall(query);
            if(customer instanceof Company) {
                statement.setInt(1,id);
                statement.setString(2, ((Company) customer).getPhone());
                statement.setString(3, ((Company) customer).getEmail());
                statement.setNull(4, Types.VARCHAR);
                statement.setNull(5, Types.VARCHAR);
                statement.setString(6, ((Company) customer).getName());
            }else if(customer instanceof Individual){
                statement.setInt(1,id);
                statement.setString(2,((Individual) customer).getEmail());
                statement.setString(3,((Individual) customer).getPhone());
                statement.setString(4,((Individual) customer).getFirstName());
                statement.setString(5,((Individual) customer).getLastName());
                statement.setNull(6, Types.VARCHAR);
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
    public boolean delete(int id) throws SQLException {
        String query = "{call DeleteCustomerByID(?)}";
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
}
