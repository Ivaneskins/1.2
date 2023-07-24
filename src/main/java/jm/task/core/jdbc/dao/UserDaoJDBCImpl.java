package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Statement stmt = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        System.out.println("Creating table in given database...");
        try (Connection conn = Util.getMySQLConnection()){
            if(conn != null) {
                stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS REGISTRATION " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " first VARCHAR(255), " +
                        " last VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY (id))");
                System.out.println("Created table in given database...");
                System.out.println("-------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        System.out.println("Dropping table in given database...");
        try (Connection conn = Util.getMySQLConnection()){
            if (conn != null) {
                stmt = conn.createStatement();
                stmt.executeUpdate("DROP TABLE IF EXISTS REGISTRATION");
                System.out.println("Dropped table in given database...");
                System.out.println("-------------------------------------");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getMySQLConnection()){
            if (conn != null) {
                System.out.println("Starting add user...");
                PreparedStatement prepStmt =
                        conn.prepareStatement("INSERT INTO registration(first, last, age) VALUES (?, ?, ?)");
                prepStmt.setString(1, name);
                prepStmt.setString(2, lastName);
                prepStmt.setByte(3, age);
                prepStmt.executeUpdate();
                System.out.println("User added successful");
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection()){
            if (conn != null) {
                System.out.println("Remove user started...");
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM registration WHERE id=?");
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
                System.out.println("Remove user is done");
            }
        } catch (SQLException sqle) {
            System.out.println("Remove user not happened");
            sqle.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getMySQLConnection()){
            if (conn != null) {
                System.out.println("Getting all users...");
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM registration");
                while (rs.next()) {
                    long id = rs.getInt(1);
                    String name = rs.getString(2);
                    String lastName = rs.getString(3);
                    byte age = rs.getByte(4);
                    User user = new User(name, lastName, age);
                    user.setId(id);
                    users.add(user);
                }
                System.out.println("All users get successful");
                System.out.println("------------------------------");
            }

        } catch (SQLException sqle) {
            System.out.println("Get users exception");
            sqle.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMySQLConnection()){
            if (conn != null) {
                System.out.println("Started clean table...");
                stmt = conn.createStatement();
                stmt.executeUpdate("TRUNCATE TABLE registration");
                System.out.println("cleaned table successful");
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Clean table exception");
        }
    }
}
