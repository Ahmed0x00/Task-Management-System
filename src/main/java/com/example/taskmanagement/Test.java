package com.example.taskmanagement;
import com.example.taskmanagement.util.LoggerUtil;
import com.example.taskmanagement.util.UserUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.taskmanagement.model.User;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;

public class Test {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> List<T> getAll(String path, TypeReference<List<T>> typeRef) {
        try {
            File file = new File(path);
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, typeRef);
        } catch (Exception e) {
            LoggerUtil.getLogger().severe("JSON Load Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static <T> T getOne(String path, TypeReference<List<T>> typeRef, Predicate<T> predicate) {
        return getAll(path, typeRef).stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }


    private static final String USER_FILE = "src/main/resources/data/users.json";
    private static final TypeReference<List<User>> TYPE = new TypeReference<>() {};

    public static List<User> getAllUsers() {
        return getAll(USER_FILE, TYPE);
    }

    public static User findByUsername(String username) {
        Predicate<User> predicate = u -> u.getUsername().equalsIgnoreCase(username);
        return getOne(USER_FILE, TYPE, predicate);
    }

    public static void main(String[] args) {
        // Get all users
        List<User> users = UserUtil.getAllUsers();
        System.out.println("----- All Users -----");
        for (User user : users) {
            System.out.println(user);
        }

        // Find one user by username
        String targetUsername = "admin";
        Predicate<User> predicate = u -> u.getUsername().equalsIgnoreCase(targetUsername);
        User foundUser = getOne(USER_FILE, TYPE, predicate);

        System.out.println("----- Found User -----");
        System.out.println(foundUser != null ? foundUser : "User not found.");
        System.out.println(foundUser != null ? "User password: " + foundUser.getPassword() : "User not found.");
    }
}
