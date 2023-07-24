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

    static final String connectionURL = "jdbc:mysql://localhost:3306/sys";
    public static Connection getMySQLConnection() {
        String userName = "root";
        String password = "root";
        try {
            Connection conn = DriverManager.getConnection(connectionURL, userName, password);
            System.out.println("Connection to DB successful");
            System.out.println("---------------------------------");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        }
        return null;
    }
}
