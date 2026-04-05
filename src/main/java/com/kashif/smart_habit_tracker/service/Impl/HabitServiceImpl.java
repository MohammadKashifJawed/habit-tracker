package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.HabitRequest;
import com.kashif.smart_habit_tracker.dto.response.HabitResponse;
import com.kashif.smart_habit_tracker.entity.Habit;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.exception.AlreadyExistsException;
import com.kashif.smart_habit_tracker.exception.ResourceNotFoundException;
import com.kashif.smart_habit_tracker.mapper.HabitMapper;
import com.kashif.smart_habit_tracker.repository.HabitRepository;
import com.kashif.smart_habit_tracker.service.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements HabitService {

    private final HabitRepository habitRepository;

    @Transactional
    @Override
    public HabitResponse createHabit(HabitRequest request) {
        Habit newHabit = HabitMapper.habitRequestToHabit(request);
        User user = getCurrentUser();
        List<Habit> userHabits = habitRepository.findByUserId(user.getId());
        if(!userHabits.isEmpty()){
            userHabits.forEach(habit -> {
                if (habit.getName().equals(newHabit.getName())){
                    throw new AlreadyExistsException(
                            "Habit already exists for this user", HttpStatus.CONFLICT
                    );
                }
            });
        }
        newHabit.setUser(user);
        userHabits.add(newHabit);
        return HabitMapper.habitToHabitResponse(habitRepository.save(newHabit));
    }

    @Override
    public void deleteHabit(Long id) {
        habitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Habit not found with provided id"));
        User user = getCurrentUser();
        List<Habit> userHabits = habitRepository.findByUserId(user.getId());
        userHabits.forEach(habit -> {
            if (habit.getId().equals(id)) {
                habitRepository.delete(habit);
                return;
            }
        });
    }

    @Override
    public HabitResponse getHabitById(Long id) {
        User user = getCurrentUser();
        Habit habit = habitRepository.findByHabitIdAndUserId(user.getId(), id);
        if (habit == null)
            throw new ResourceNotFoundException("Habit with provided id not found");
        return HabitMapper.habitToHabitResponse(habit);
    }

    @Override
    public List<HabitResponse> getHabits() {
        User user = getCurrentUser();
        List<Habit> habits = habitRepository.findByUserId(user.getId());
        return HabitMapper.habitListToHabitResponseList(habits);
    }

    private User getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null)
            throw new ResourceNotFoundException("User not logged in");
        return user;
    }


}
