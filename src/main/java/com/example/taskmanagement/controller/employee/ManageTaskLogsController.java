package com.example.taskmanagement.controller.employee;

import com.example.taskmanagement.model.TaskLog;
import com.example.taskmanagement.util.TaskLogUtil;
import com.example.taskmanagement.util.UserUtil;
import com.example.taskmanagement.util.EmployeeUtil;
import com.example.taskmanagement.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;

public class ManageTaskLogsController {

    @FXML
    private GridPane taskLogGrid;

    private static final int COLUMNS = 4;

    private User loggedInUser;

    @FXML
    public void initialize() {
        loggedInUser = UserUtil.getLoggedInUser();
        loadTaskLogs();
    }

    private void loadTaskLogs() {
        taskLogGrid.getChildren().clear();
        List<TaskLog> taskLogs;
        if (loggedInUser.getRole() == User.Role.EMPLOYEE) {
            String employeeId = loggedInUser.getEmployeeId();
            if (employeeId == null) {
                return; // No logs if no employeeId
            }
            String employeeName = EmployeeUtil.getById(employeeId).getName();
            taskLogs = TaskLogUtil.getAllTaskLogs().stream()
                    .filter(log -> log.getEmployee() != null && log.getEmployee().equals(employeeName))
                    .collect(Collectors.toList());
        } else {
            taskLogs = TaskLogUtil.getAllTaskLogs();
        }

        int col = 0;
        int row = 0;

        for (TaskLog log : taskLogs) {
            VBox card = new VBox(10);
            card.setStyle("-fx-border-color: #ccc; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 10; -fx-background-color: #f2f2f2;");
            card.setPrefWidth(200);

            Label taskNameLabel = new Label("Task: " + log.getTaskName());
            Label employeeLabel = new Label("Employee: " + log.getEmployee());
            Label startedAtLabel = new Label("Started: " + (log.getStartedAt() != null ? log.getStartedAt() : "None"));
            Label endedAtLabel = new Label("Ended: " + (log.getEndedAt() != null ? log.getEndedAt() : "None"));
            Label timeSpentLabel = new Label("Time Spent: " + (log.getTimeSpent() != null ? log.getTimeSpent() : "None"));

            card.getChildren().addAll(taskNameLabel, employeeLabel, startedAtLabel, endedAtLabel, timeSpentLabel);
            taskLogGrid.add(card, col, row);
            col++;
            if (col >= COLUMNS) {
                col = 0;
                row++;
            }
        }
    }
}