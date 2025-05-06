package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Project;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class ProjectUtil {

    private static final String PROJECT_FILE = "src/main/resources/data/projects.json";
    private static final TypeReference<List<Project>> TYPE = new TypeReference<>() {};

    public static List<Project> getAllProjects() {
        return JsonUtil.getAll(PROJECT_FILE, TYPE);
    }

    public static Project findByName(String name) {
        Predicate<Project> predicate = p -> p.getName().equalsIgnoreCase(name);
        return JsonUtil.getOne(PROJECT_FILE, TYPE, predicate);
    }

    public static void addProject(Project project) {
        JsonUtil.add(PROJECT_FILE, project, TYPE);
    }

    public static void updateProject(Project project) {
        JsonUtil.edit(PROJECT_FILE, p -> p.getId().equals(project.getId()), project, TYPE);
    }

    public static void deleteProjectById(String id) {
        JsonUtil.delete(PROJECT_FILE, p -> p.getId().equals(id), TYPE);
    }

    public static Project getById(String id) {
        return JsonUtil.getOne(PROJECT_FILE, TYPE, p -> p.getId().equals(id));
    }
}