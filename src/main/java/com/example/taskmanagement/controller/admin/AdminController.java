package com.example.taskmanagement.controller.admin;

import com.example.taskmanagement.util.ViewLoaderUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class AdminController {

    @FXML
    private StackPane mainContent;

    @FXML
    public void initialize() {
        // No need to load AdminDashboard.fxml; it's already loaded by AuthController
        System.out.println("AdminController initialized");
    }

    public void loadDashboardView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/AdminDashboard.fxml");
    }

    public void loadManageUsersView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageUsers.fxml");
    }

    public void loadManageEmployeesView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageEmployees.fxml");
    }

    public void loadManageEmployeeTypesView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageEmployeeTypes.fxml");
    }

    public void loadManageProjectsView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageProjects.fxml");
    }

    public void loadManageCustomersView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageCustomers.fxml");
    }

    public void loadManageTasksView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ManageTasks.fxml");
    }

    public void loadManageTasksPhasesView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Admin/ManageTaskPhases.fxml");
    }
}