package com.kashif.smart_habit_tracker.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HabitResponse {
    private String name;
    private String description;
    private String frequency;
    private Integer goal;
}
