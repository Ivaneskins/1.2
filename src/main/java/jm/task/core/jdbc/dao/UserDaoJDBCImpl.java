package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY (id))";

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

    public void saveUser(String name, String lastName, byte age) {
        try {
            System.out.println("Starting add user...");
            String sql = "INSERT INTO registration(first, last, age) VALUES (?, ?, ?)";
            PreparedStatement prepStmt = conn.prepareStatement(sql);
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setByte(3, age);
            prepStmt.executeUpdate();
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

        try {
            System.out.println("Remove user started...");
            String sql = "DELETE FROM registration WHERE id=1";
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Remove user is done");
        } catch (SQLException sqle) {
            System.out.println("Remove user not happened");
            sqle.printStackTrace();
        } finally {
            finalDontRepeat();
        }
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
