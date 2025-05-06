package com.example.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {

    @JsonProperty("id")
    private String id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("assignedEmployeeId")
    private String assignedEmployeeId;

    @JsonProperty("projectId")
    private String projectId;

    @JsonProperty("phase")
    private String phase; // Now stores TaskPhase ID

    @JsonProperty("priority")
    private Priority priority;

    @JsonProperty("creatorUserId")
    private String creatorUserId;

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("endDate")
    private String endDate;

    @JsonProperty("estimatedHours")
    private Double estimatedHours;

    public enum Priority { LOW, MEDIUM, HIGH }

    public Task() {}

    public Task(String id, String code, String title, String description, String assignedEmployeeId,
                String projectId, String phase, Priority priority, String creatorUserId,
                String startDate, String endDate, Double estimatedHours) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.assignedEmployeeId = assignedEmployeeId;
        this.projectId = projectId;
        this.phase = phase;
        this.priority = priority;
        this.creatorUserId = creatorUserId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedHours = estimatedHours;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAssignedEmployeeId() { return assignedEmployeeId; }
    public void setAssignedEmployeeId(String assignedEmployeeId) { this.assignedEmployeeId = assignedEmployeeId; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getPhase() { return phase; }
    public void setPhase(String phase) { this.phase = phase; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getCreatorUserId() { return creatorUserId; }
    public void setCreatorUserId(String creatorUserId) { this.creatorUserId = creatorUserId; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public Double getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(Double estimatedHours) { this.estimatedHours = estimatedHours; }
}