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

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/sys";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getMySQLConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
            System.out.println("Connection to DB successful");
            System.out.println("---------------------------------");
        } catch (SQLException e) {
            System.out.println("Connection exception");
            e.printStackTrace();
        }
        return conn;
    }
}
