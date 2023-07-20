package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection conn = null;
    Statement stmt = null;

    public UserDaoJDBCImpl() {
        try {
            conn = Util.getMySQLConnection();
        }
        catch (SQLException e) {
            System.out.println("Sql exception happened");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception happened");
        }
    }

    public void createUsersTable() {
        System.out.println("Creating table in given database...");
        try {
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
            System.out.println("-------------------------------------------");
        } catch (SQLException se) {
            se.printStackTrace();

        } finally {
            //finally block used to close resources
            finalDontRepeat();
        }
    }

    public void dropUsersTable() {
        System.out.println("Dropping table in given database...");
        try {
            stmt = conn.createStatement();

            String sql = "DROP TABLE IF EXISTS REGISTRATION";

            stmt.executeUpdate(sql);
            System.out.println("Dropped table in given database...");
            System.out.println("-------------------------------------");
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            //finally block used to close resources
            finalDontRepeat();
        }
    }

    public void saveUser(String name, String lastName, byte ageUser) {
        try {
            System.out.println("Starting add user...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO registration(id, first, ageUser) " +
                    " values (first, last, age) ";
            stmt.executeUpdate(sql);
            System.out.println("User added successful");
            System.out.println("------------------------------");
        } catch (SQLException sqle) {
//            System.out.println("User added exception");
            sqle.printStackTrace();
        } finally {
            //finally block used to close resources
            finalDontRepeat();
        }

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        try {
            System.out.println("Started clean table...");
            stmt = conn.createStatement();
            String sql = "TRUNCATE TABLE registration";
            stmt.executeUpdate(sql);
            System.out.println("cleaned table successful");
            System.out.println("------------------------------");
        } catch (SQLException sqle) {
            System.out.println("Clean table exception");
        } finally {
            //finally block used to close resources
            finalDontRepeat();
        }
    }

    public void finalDontRepeat() {
        try {
            if (stmt != null)
                conn.close();
        } catch (SQLException se) {
        }// do nothing
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }//end finally try
    }
}
