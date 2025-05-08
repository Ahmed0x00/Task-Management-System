package com.example.taskmanagement.controller.employee;

import com.example.taskmanagement.util.ViewLoaderUtil;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

public class EmployeeController {

    @FXML
    private StackPane mainContent;

    @FXML
    public void initialize() {
        System.out.println("EmployeeController initialized");
    }

    public void loadManageTasksView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ManageTasks.fxml");
    }

    public void loadManageTaskLogsView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ManageTaskLogs.fxml");
    }

    public void loadManageLeaveTypesView() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ManageLeaveTypes.fxml");
    }

    public void loadViewRequests() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ViewRequests.fxml");
    }

    public void loadTimeCards() {
        ViewLoaderUtil.loadView(mainContent, "/com/example/taskmanagement/Employee/ViewTimeCards.fxml");
    }
}