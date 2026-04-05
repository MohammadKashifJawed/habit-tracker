package com.kashif.smart_habit_tracker.mapper;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import com.kashif.smart_habit_tracker.entity.Habit;
import com.kashif.smart_habit_tracker.entity.enums.Frequency;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HabitMapper {

    public static Habit habitRequestToHabit(HabitRequest request){
        Habit habit = new Habit();
        habit.setName(request.getName());
        habit.setDescription(request.getDescription());
        habit.setFrequency(Frequency.valueOf(request.getFrequency()));
        habit.setGoal(request.getGoal());
        return habit;
    }

    public static HabitResponse habitToHabitResponse(Habit habit){
        return HabitResponse.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .frequency(habit.getFrequency().toString())
                .goal(habit.getGoal())
                .build();
    }

    public static List<HabitResponse> habitListToHabitResponseList(List<Habit> habits){
        List<HabitResponse> responses = new ArrayList<>();
        for (Habit habit : habits){
            HabitResponse response = HabitResponse.builder()
                    .name(habit.getName())
                    .description(habit.getDescription())
                    .frequency(habit.getFrequency().toString())
                    .goal(habit.getGoal())
                    .build();
            responses.add(response);
        }
        return responses;
    }
}
