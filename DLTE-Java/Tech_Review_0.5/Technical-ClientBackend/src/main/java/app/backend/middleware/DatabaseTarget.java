package app.backend.middleware;

import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseTarget  {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private static Logger logger = LoggerFactory.getLogger(DatabaseTarget.class);
    private static Connection connection;

    public static Connection initializeConnection() {
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(
                    resourceBundle.getString("db.url"),
                    resourceBundle.getString("db.user"),
                    resourceBundle.getString("db.pass")
            );
            logger.info(resourceBundle.getString("db.server.ok"));

        } catch (SQLException e) {
            logger.error(resourceBundle.getString("db.server.error"), e);
            e.printStackTrace();
        }
        return connection;
    }

}
