package com.kashif.smart_habit_tracker.dto.response;

import lombok.Data;

@Data
public class HabitResponse {
    private Long id;
    private String name;
    private String description;
    private String frequency;
    private Integer goal;
    private int currentStreak;
    private int longestStreak;
}
