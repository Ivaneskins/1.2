package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        //1 create table
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
//
//        //2 add 4 users
//        UserServiceImpl userService3 = new UserServiceImpl();
//        userService3.saveUser("Ivan", "Vas", (byte)35);
//        userService3 = new UserServiceImpl();
//        userService3.saveUser("Sofia", "Vas", (byte)35);
//        userService3 = new UserServiceImpl();
//        userService3.saveUser("Fil", "Suv", (byte)35);
//        userService3 = new UserServiceImpl();
//        userService3.saveUser("Artem", "Ki", (byte)35);

        //3 get all users
//        UserServiceImpl userService5 = new UserServiceImpl();
//        userService5.getAllUsers().stream().forEach(System.out::println);

//        4 clean table
//        UserServiceImpl userService2 = new UserServiceImpl();
//        userService2.cleanUsersTable();

//        5 drop table
//        UserServiceImpl userService1 = new UserServiceImpl();
//        userService1.dropUsersTable();

        //Delete user by id
//        UserServiceImpl userService4 = new UserServiceImpl();
//        userService4.removeUserById(20);
    }
}
