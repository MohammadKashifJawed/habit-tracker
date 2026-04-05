package com.kashif.smart_habit_tracker.controller;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.ApiResponse;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import com.kashif.smart_habit_tracker.service.HabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;

    @PostMapping
    public ResponseEntity<ApiResponse<HabitResponse>> createHabit(@RequestBody @Valid HabitRequest request){
        return ResponseEntity.ok(ApiResponse.success(
                "Habit created",
                habitService.createHabit(request)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HabitResponse>> getHabit(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(
                "Habit fetched successfully",
                habitService.getHabitById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HabitResponse>>> getAllHabits(){
        return ResponseEntity.ok(ApiResponse.success(
                "Habits fetched successfully",
                habitService.getHabits()
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id){
        habitService.deleteHabit(id);
        return new ResponseEntity<>("Habit deleted successfully", HttpStatus.NO_CONTENT);
    }
}
