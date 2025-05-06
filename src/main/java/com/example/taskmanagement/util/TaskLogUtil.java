package com.example.taskmanagement.util;

import com.example.taskmanagement.model.TaskLog;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class TaskLogUtil {

    private static final String TASK_LOG_FILE = "src/main/resources/data/task_logs.json";
    private static final TypeReference<List<TaskLog>> TYPE = new TypeReference<>() {};

    public static List<TaskLog> getAllTaskLogs() {
        return JsonUtil.getAll(TASK_LOG_FILE, TYPE);
    }

    public static void addTaskLog(TaskLog taskLog) {
        JsonUtil.add(TASK_LOG_FILE, taskLog, TYPE);
    }

    public static void updateTaskLog(TaskLog taskLog) {
        Predicate<TaskLog> predicate = log -> log.getId() != null && log.getId().equals(taskLog.getId());
        JsonUtil.edit(TASK_LOG_FILE, predicate, taskLog, TYPE);
    }

    public static TaskLog getByTaskIdAndEmployeeId(String taskId, String employeeId) {
        if (taskId == null || employeeId == null) {
            return null;
        }
        String taskTitle = TaskUtil.getById(taskId) != null ? TaskUtil.getById(taskId).getTitle() : null;
        String employeeName = EmployeeUtil.getById(employeeId) != null ? EmployeeUtil.getById(employeeId).getName() : null;
        if (taskTitle == null || employeeName == null) {
            return null;
        }
        Predicate<TaskLog> predicate = log -> log.getTaskName() != null && log.getTaskName().equals(taskTitle) &&
                log.getEmployee() != null && log.getEmployee().equals(employeeName) && log.getEndedAt() == null;
        return JsonUtil.getOne(TASK_LOG_FILE, TYPE, predicate);
    }
}