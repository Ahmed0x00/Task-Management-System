package com.example.taskmanagement.util;

import com.example.taskmanagement.model.LeaveType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.function.Predicate;

public class LeaveTypeUtil {

    private static final String LEAVE_TYPE_FILE = "src/main/resources/data/leave_types.json";
    private static final TypeReference<List<LeaveType>> TYPE = new TypeReference<>() {};

    public static List<LeaveType> getAllLeaveTypes() {
        return JsonUtil.getAll(LEAVE_TYPE_FILE, TYPE);
    }

    public static LeaveType getById(String id) {
        if (id == null) {
            return null;
        }
        Predicate<LeaveType> predicate = lt -> lt.getId() != null && lt.getId().equals(id);
        return JsonUtil.getOne(LEAVE_TYPE_FILE, TYPE, predicate);
    }

    public static void addLeaveType(LeaveType leaveType) {
        JsonUtil.add(LEAVE_TYPE_FILE, leaveType, TYPE);
    }

    public static void updateLeaveType(LeaveType leaveType) {
        Predicate<LeaveType> predicate = lt -> lt.getId() != null && lt.getId().equals(leaveType.getId());
        JsonUtil.edit(LEAVE_TYPE_FILE, predicate, leaveType, TYPE);
    }

    public static void deleteLeaveTypeById(String id) {
        Predicate<LeaveType> predicate = lt -> lt.getId() != null && lt.getId().equals(id);
        JsonUtil.delete(LEAVE_TYPE_FILE, predicate, TYPE);
    }
}