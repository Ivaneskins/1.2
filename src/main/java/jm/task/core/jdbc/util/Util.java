package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    // Connect to MySQL
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {

        String propertyPath = "C:\\java\\Kata\\PreProject\\1\\src\\main\\resources\\config.properties";


//        String hostName = "localhost";
//        String dbName = "sys";
//        String userName = "root";
//        String password = "2010goblin";
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(propertyPath)) {
            prop.load(fis);
            String hostName = prop.getProperty("hostName");
            String dbName = prop.getProperty("dbName");
            String userName = prop.getProperty("userName");
            String password = prop.getProperty("password");
            return getMySQLConnection(hostName, dbName, userName, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException
                                                {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
//        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        System.out.println("Connection to DB successful");
        System.out.println("---------------------------------");
        return conn;
    }

}
