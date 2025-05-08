package com.example.taskmanagement.util;

import com.example.taskmanagement.model.Request;
import com.example.taskmanagement.model.TimeCard;
import com.fasterxml.jackson.core.type.TypeReference;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RequestUtil {
    private static final String REQUESTS_FILE = "src/main/resources/data/requests.json";
    private static final TypeReference<List<Request>> TYPE = new TypeReference<>() {};
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static List<Request> getAllRequests() {
        return JsonUtil.getAll(REQUESTS_FILE, TYPE);
    }

    public static List<Request> getRequestsByEmployee(String employeeId) {
        return getAllRequests().stream()
                .filter(req -> req.getEmployeeId().equals(employeeId))
                .toList();
    }

    public static boolean addRequest(Request request) {
        try {
            List<Request> requests = getAllRequests();
            int newId = requests.stream().mapToInt(Request::getId).max().orElse(0) + 1;
            request.setId(newId);
            JsonUtil.add(REQUESTS_FILE, request, TYPE);
            System.out.println("RequestUtil.addRequest: Request added with ID: " + newId);
            return true;
        } catch (Exception e) {
            System.out.println("RequestUtil.addRequest: Error adding request: " + e.getMessage());
            return false;
        }
    }

    public static boolean updateRequest(Request request) {
        try {
            JsonUtil.edit(REQUESTS_FILE, r -> r.getId() == request.getId(), request, TYPE);
            System.out.println("RequestUtil.updateRequest: Request updated with ID: " + request.getId());
            // Create time cards for approved LEAVE requests
            if ("LEAVE".equals(request.getType()) && request.getStatus() == Request.Status.APPROVED) {
                createTimeCardsForLeave(request);
            }
            return true;
        } catch (Exception e) {
            System.out.println("RequestUtil.updateRequest: Error updating request: " + e.getMessage());
            return false;
        }
    }

    private static void createTimeCardsForLeave(Request request) {
        try {
            LocalDate startDate = LocalDate.parse(request.getStartDate(), DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(request.getEndDate(), DATE_FORMATTER);
            for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                TimeCard timeCard = new TimeCard(
                        0, // ID will be set by TimeCardUtil
                        request.getEmployeeId(),
                        null, // No clock-in for leave
                        null, // No clock-out for leave
                        date.format(DATE_FORMATTER),
                        0.0 // Zero hours worked
                );
                TimeCardUtil.addTimeCard(timeCard);
                System.out.println("RequestUtil.createTimeCardsForLeave: Added time card for date: " + date);
            }
        } catch (Exception e) {
            System.out.println("RequestUtil.createTimeCardsForLeave: Error creating time card: " + e.getMessage());
        }
    }
}