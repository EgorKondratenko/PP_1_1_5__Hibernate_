package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Егор", "Кондратенко", (byte) 18);
        userService.saveUser("Рубик", "Кондратенко", (byte) 1);
        userService.saveUser("Гена", "Крокодил", (byte) 100);
        userService.saveUser("Чебурашка", "Апельсиновый", (byte) 30);
        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
//        userService.dropUsersTable();

    }
}
