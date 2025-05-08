package com.example.taskmanagement.util;

import com.example.taskmanagement.model.User;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class UserUtil {
    private static final String USER_FILE = "src/main/resources/data/users.json";
    private static final TypeReference<List<User>> TYPE = new TypeReference<>() {};
    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static List<User> getAllUsers() {
        return JsonUtil.getAll(USER_FILE, TYPE);
    }

    public static User findByUsername(String username) {
        Predicate<User> predicate = u -> u.getUsername().equalsIgnoreCase(username);
        return JsonUtil.getOne(USER_FILE, TYPE, predicate);
    }

    public static void addUser(User user) {
        JsonUtil.add(USER_FILE, user, TYPE);
    }

    public static void updateUser(User user) {
        JsonUtil.edit(USER_FILE, u -> u.getId().equals(user.getId()), user, TYPE);
    }

    public static void deleteUserById(String id) {
        JsonUtil.delete(USER_FILE, u -> u.getId().equals(id), TYPE);
    }

    public static User getById(String id) {
        return JsonUtil.getOne(USER_FILE, TYPE, u -> u.getId().equals(id));
    }

    public static String getEmployeeName(String employeeId) {
        if (employeeId == null || employeeId.isEmpty()) {
            System.out.println("EmployeeUtil.getEmployeeName: Invalid employeeId: " + employeeId);
            return "Unknown";
        }
        String name = getAllUsers().stream()
                .filter(user -> user.getEmployeeId() != null && user.getEmployeeId().equals(employeeId))
                .map(User::getUsername)
                .findFirst()
                .orElse("Unknown");
        if (name.equals("Unknown")) {
            System.out.println("EmployeeUtil.getEmployeeName: No user found for employeeId: " + employeeId);
        }
        return name;
    }
}