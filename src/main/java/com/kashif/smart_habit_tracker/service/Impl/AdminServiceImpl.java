package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.mapper.UserMapper;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> responseList = new ArrayList<>();
        users.forEach(user -> responseList.add(UserMapper.userToUserResponse(user)));
        System.out.println(responseList);
        return responseList;
    }
}
