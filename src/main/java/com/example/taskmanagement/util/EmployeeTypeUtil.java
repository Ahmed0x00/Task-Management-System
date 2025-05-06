package com.example.taskmanagement.util;

import com.example.taskmanagement.model.EmployeeType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class EmployeeTypeUtil {

    private static final String EMPLOYEE_TYPE_FILE = "src/main/resources/data/employee_types.json";
    private static final TypeReference<List<EmployeeType>> TYPE = new TypeReference<>() {};

    public static List<EmployeeType> getAllEmployeeTypes() {
        return JsonUtil.getAll(EMPLOYEE_TYPE_FILE, TYPE);
    }

    public static EmployeeType findByTitle(String title) {
        Predicate<EmployeeType> predicate = et -> et.getTitle().equalsIgnoreCase(title);
        return JsonUtil.getOne(EMPLOYEE_TYPE_FILE, TYPE, predicate);
    }

    public static void addEmployeeType(EmployeeType employeeType) {
        List<EmployeeType> employeeTypes = getAllEmployeeTypes();
        if (employeeType.getId() == 0) {
            int newId = employeeTypes.stream()
                    .mapToInt(EmployeeType::getId)
                    .max()
                    .orElse(0) + 1;
            employeeType.setId(newId);
        }
        JsonUtil.add(EMPLOYEE_TYPE_FILE, employeeType, TYPE);
    }

    public static void updateEmployeeType(EmployeeType employeeType) {
        JsonUtil.edit(EMPLOYEE_TYPE_FILE, et -> et.getId() == employeeType.getId(), employeeType, TYPE);
    }

    public static void deleteEmployeeTypeById(int id) {
        JsonUtil.delete(EMPLOYEE_TYPE_FILE, et -> et.getId() == id, TYPE);
    }

    public static EmployeeType getById(int id) {
        return JsonUtil.getOne(EMPLOYEE_TYPE_FILE, TYPE, et -> et.getId() == id);
    }
}