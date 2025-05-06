package com.example.taskmanagement.util;

import com.example.taskmanagement.model.TaskPhase;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class TaskPhaseUtil {

    private static final String TASK_PHASE_FILE = "src/main/resources/data/task_phases.json";
    private static final TypeReference<List<TaskPhase>> TYPE = new TypeReference<>() {};

    public static List<TaskPhase> getAllTaskPhases() {
        return JsonUtil.getAll(TASK_PHASE_FILE, TYPE);
    }

    public static TaskPhase findByName(String name) {
        Predicate<TaskPhase> predicate = p -> p.getName().equalsIgnoreCase(name);
        return JsonUtil.getOne(TASK_PHASE_FILE, TYPE, predicate);
    }

    public static void addTaskPhase(TaskPhase taskPhase) {
        JsonUtil.add(TASK_PHASE_FILE, taskPhase, TYPE);
    }

    public static void updateTaskPhase(TaskPhase taskPhase) {
        JsonUtil.edit(TASK_PHASE_FILE, p -> p.getId().equals(taskPhase.getId()), taskPhase, TYPE);
    }

    public static void deleteTaskPhaseById(String id) {
        JsonUtil.delete(TASK_PHASE_FILE, p -> p.getId().equals(id), TYPE);
    }

    public static TaskPhase getById(String id) {
        return JsonUtil.getOne(TASK_PHASE_FILE, TYPE, p -> p.getId().equals(id));
    }
}