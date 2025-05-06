package com.example.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskLog {

    @JsonProperty("id")
    private String id;

    @JsonProperty("taskName")
    private String taskName;

    @JsonProperty("employee")
    private String employee;

    @JsonProperty("startedAt")
    private String startedAt;

    @JsonProperty("endedAt")
    private String endedAt;

    @JsonProperty("timeSpent")
    private String timeSpent;

    public TaskLog() {}

    public TaskLog(String id, String taskName, String employee, String startedAt, String endedAt, String timeSpent) {
        this.id = id;
        this.taskName = taskName;
        this.employee = employee;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.timeSpent = timeSpent;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getEmployee() { return employee; }
    public void setEmployee(String employee) { this.employee = employee; }

    public String getStartedAt() { return startedAt; }
    public void setStartedAt(String startedAt) { this.startedAt = startedAt; }

    public String getEndedAt() { return endedAt; }
    public void setEndedAt(String endedAt) { this.endedAt = endedAt; }

    public String getTimeSpent() { return timeSpent; }
    public void setTimeSpent(String timeSpent) { this.timeSpent = timeSpent; }
}