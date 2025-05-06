package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class EmployeeUtil {

    private static final String EMPLOYEE_FILE = "src/main/resources/data/employees.json";
    private static final TypeReference<List<Employee>> TYPE = new TypeReference<>() {};

    public static List<Employee> getAllEmployees() {
        return JsonUtil.getAll(EMPLOYEE_FILE, TYPE);
    }

    public static Employee findByEmail(String email) {
        Predicate<Employee> predicate = e -> e.getEmail().equalsIgnoreCase(email);
        return JsonUtil.getOne(EMPLOYEE_FILE, TYPE, predicate);
    }

    public static void addEmployee(Employee employee) {
        if (employee.getEmployeeTypeId() != 0 && EmployeeTypeUtil.getById(employee.getEmployeeTypeId()) == null) {
            throw new IllegalArgumentException("Invalid employeeTypeId: " + employee.getEmployeeTypeId());
        }
        JsonUtil.add(EMPLOYEE_FILE, employee, TYPE);
    }

    public static void updateEmployee(Employee employee) {
        if (employee.getEmployeeTypeId() != 0 && EmployeeTypeUtil.getById(employee.getEmployeeTypeId()) == null) {
            throw new IllegalArgumentException("Invalid employeeTypeId: " + employee.getEmployeeTypeId());
        }
        JsonUtil.edit(EMPLOYEE_FILE, e -> e.getId().equals(employee.getId()), employee, TYPE);
    }

    public static void deleteEmployeeById(String id) {
        JsonUtil.delete(EMPLOYEE_FILE, e -> e.getId().equals(id), TYPE);
    }

    public static Employee getById(String id) {
        return JsonUtil.getOne(EMPLOYEE_FILE, TYPE, e -> e.getId().equals(id));
    }
}