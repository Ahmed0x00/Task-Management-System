package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class TaskUtil {

    private static final String TASK_FILE = "src/main/resources/data/tasks.json";
    private static final TypeReference<List<Task>> TYPE = new TypeReference<>() {};

    public static List<Task> getAllTasks() {
        return JsonUtil.getAll(TASK_FILE, TYPE);
    }

    public static List<Task> getTasksByEmployeeId(String employeeId) {
        Predicate<Task> predicate = t -> employeeId.equals(t.getAssignedEmployeeId());
        return getAllTasks().stream().filter(predicate).toList();
    }

    public static Task findByCode(String code) {
        Predicate<Task> predicate = t -> t.getCode().equalsIgnoreCase(code);
        return JsonUtil.getOne(TASK_FILE, TYPE, predicate);
    }

    public static void addTask(Task task) {
        JsonUtil.add(TASK_FILE, task, TYPE);
    }

    public static void updateTask(Task task) {
        JsonUtil.edit(TASK_FILE, t -> t.getId().equals(task.getId()), task, TYPE);
    }

    public static void deleteTaskById(String id) {
        JsonUtil.delete(TASK_FILE, t -> t.getId().equals(id), TYPE);
    }

    public static Task getById(String id) {
        return JsonUtil.getOne(TASK_FILE, TYPE, t -> t.getId().equals(id));
    }
}