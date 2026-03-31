package com.kashif.smart_habit_tracker.repository;

import com.kashif.smart_habit_tracker.entity.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    Habit findByName(String name);
}
