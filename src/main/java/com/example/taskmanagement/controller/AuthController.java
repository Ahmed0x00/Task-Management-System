package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.User;
import com.example.taskmanagement.model.User.Role;
import com.example.taskmanagement.util.LoggerUtil;
import com.example.taskmanagement.util.UserUtil;
import com.example.taskmanagement.util.ViewLoaderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class AuthController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    @FXML private Pane rootPane; // Add this in your FXML as the main container

    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        User user = UserUtil.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            LoggerUtil.getLogger().info("Login successful: " + username);
            UserUtil.setLoggedInUser(user);

            Role role = user.getRole(); // ADMIN / LEADER
            String view = switch (role) {
                case ADMIN -> "/com/example/taskmanagement/Admin/AdminDashboard.fxml";
                case LEADER, EMPLOYEE -> "/com/example/taskmanagement/Employee/EmployeeDashboard.fxml";
            };

            LoggerUtil.getLogger().info("Using ViewLoaderUtil to load: " + view);
            ViewLoaderUtil.loadView(rootPane, view);

        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
}
