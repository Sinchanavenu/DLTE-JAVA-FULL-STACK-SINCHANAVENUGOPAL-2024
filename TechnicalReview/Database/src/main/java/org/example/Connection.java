package org.example;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Connection {
    static java.sql.Connection connection;
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    public static Connection createConnection( )  {
        try {
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.password"));
        } catch (SQLException e) {
            //throw new EmployeeNotFoundException();
            System.out.println(e);
        }
        return (Connection) connection;
    }
}
