package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        //create table
//        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
//        userDaoJDBC.createUsersTable();

        //drop table
//        UserDaoJDBCImpl userDaoJDBC1 = new UserDaoJDBCImpl();
//        userDaoJDBC1.dropUsersTable();

        //clean table
//        UserDaoJDBCImpl userDaoJDBC2 = new UserDaoJDBCImpl();
//        userDaoJDBC2.cleanUsersTable();

        //add user
//        UserDaoJDBCImpl userDaoJDBC3 = new UserDaoJDBCImpl();
//        userDaoJDBC3.saveUser("Test", "You", (byte)80);

        UserDaoJDBCImpl userDaoJDBC4 = new UserDaoJDBCImpl();
        userDaoJDBC4.removeUserById(4);

    }
}
