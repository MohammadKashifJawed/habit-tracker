package com.kashif.smart_habit_tracker.repository;

import com.kashif.smart_habit_tracker.entity.Habit;
import com.kashif.smart_habit_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    Habit findByName(String name);

    @Query("SELECT h FROM Habit h JOIN h.user u WHERE u.id = :userId")
    List<Habit> findByUserId(@Param("userId") Long userId);

    @Query("SELECT h FROM Habit h JOIN h.user u WHERE u.id = :userId AND h.id = :habitId")
    Habit findByHabitIdAndUserId(Long userId, Long habitId);
}
