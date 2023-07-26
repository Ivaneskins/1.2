package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        // 1 create table
        userService.createUsersTable();

        // 2 add 4 users
        userService.saveUser("Ivan", "Vas", (byte)35);
        userService.saveUser("Sofia", "Vas", (byte)35);
        userService.saveUser("Fil", "Suv", (byte)35);
        userService.saveUser("Artem", "Ki", (byte)35);

        // 3 get all users
        userService.getAllUsers().stream().forEach(System.out::println);

        // 4 clean table
        userService.cleanUsersTable();

        // 5 drop table
        userService.dropUsersTable();

        // close resources
        if (UserDaoJDBCImpl.CONNECTION != null) {
            try {
                UserDaoJDBCImpl.CONNECTION.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

            //Delete user by id
//        userService.removeUserById(1L);
    }
}
