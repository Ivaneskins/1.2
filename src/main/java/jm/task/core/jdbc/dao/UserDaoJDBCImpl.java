package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static Connection CONNECTION = Util.getMySQLConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()){
            if(CONNECTION != null) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS REGISTRATION " +
                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                        " first VARCHAR(255), " +
                        " last VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY (id))");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()){
            if (CONNECTION != null) {
                statement.executeUpdate("DROP TABLE IF EXISTS REGISTRATION");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            if (CONNECTION != null) {
                PreparedStatement preparedStatement =
                        CONNECTION.prepareStatement("INSERT INTO registration(first, last, age) VALUES (?, ?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            if (CONNECTION != null) {
                PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM registration WHERE id=?");
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM registration")){
            if (CONNECTION != null) {
                while (rs.next()) {
                    long id = rs.getInt(1);
                    String name = rs.getString(2);
                    String lastName = rs.getString(3);
                    byte age = rs.getByte(4);
                    User user = new User(name, lastName, age);
                    user.setId(id);
                    users.add(user);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement();){
            if (CONNECTION != null) {
                statement.executeUpdate("TRUNCATE TABLE registration");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
