package com.example.taskmanagement.util;

import com.example.taskmanagement.model.TimeCard;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

public class TimeCardUtil {
    private static final String TIME_CARDS_FILE = "src/main/resources/data/time_cards.json";
    private static final TypeReference<List<TimeCard>> TYPE = new TypeReference<>() {};

    public static List<TimeCard> getAllTimeCards() {
        return JsonUtil.getAll(TIME_CARDS_FILE, TYPE);
    }

    public static List<TimeCard> getTimeCardsByEmployee(String employeeId) {
        return getAllTimeCards().stream()
                .filter(tc -> tc.getEmployeeId().equals(employeeId))
                .toList();
    }

    public static boolean addTimeCard(TimeCard timeCard) {
        try {
            List<TimeCard> timeCards = getAllTimeCards();
            int newId = timeCards.stream().mapToInt(TimeCard::getId).max().orElse(0) + 1;
            timeCard.setId(newId);
            JsonUtil.add(TIME_CARDS_FILE, timeCard, TYPE);
            System.out.println("TimeCardUtil.addTimeCard: Time card added with ID: " + newId);
            return true;
        } catch (Exception e) {
            System.out.println("TimeCardUtil.addTimeCard: Error adding time card: " + e.getMessage());
            return false;
        }
    }
}