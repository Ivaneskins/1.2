package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    // Connect to MySQL

    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_db?useSSL=false");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/sys?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "none");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }




}
