package org.unibl.etf;

import org.mindrot.jbcrypt.BCrypt;
import org.unibl.etf.GUI.LoginFrame;
import org.unibl.etf.GUI.MainFrame;
import org.unibl.etf.UTIL.ConnectionPool;

import java.sql.*;

public class Main {
    public static LoginFrame loginFrame;
    public static MainFrame mainFrame;
    public static void main(String[] args) {
          loginFrame = new LoginFrame();
          loginFrame.setVisible(true);
    }

    private static void createEmployee(String FirstName, String LastName, String Email, String Phone, String Address, String City, String PostCode, String username, String plainPassword){
        var connectionPool = ConnectionPool.getInstance();
        Connection connection = null;
        CallableStatement statement=null;

        try {
            connection=connectionPool.checkOut();

            // Hashing the password using BCrypt
            String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());

            // Inserting the user information into the database
            String insertQuery = "INSERT INTO employee (FirstName,LastName,Email,Phone,Address,City,PostCode,Username, Password) VALUES (?, ?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);


            preparedStatement.setString(1, FirstName);
            preparedStatement.setString(2, LastName);
            preparedStatement.setString(3, Email);
            preparedStatement.setString(4, Phone);
            preparedStatement.setString(5, Address);
            preparedStatement.setString(6, City);
            preparedStatement.setString(7, PostCode);
            preparedStatement.setString(8, username);
            preparedStatement.setString(9, hashedPassword);


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }

            // Closing the resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
