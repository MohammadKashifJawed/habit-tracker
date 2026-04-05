package com.kashif.smart_habit_tracker.service.Impl;

import com.kashif.smart_habit_tracker.dto.request.UserRequest;
import com.kashif.smart_habit_tracker.dto.response.UserResponse;
import com.kashif.smart_habit_tracker.entity.User;
import com.kashif.smart_habit_tracker.exception.ResourceNotFoundException;
import com.kashif.smart_habit_tracker.mapper.UserMapper;
import com.kashif.smart_habit_tracker.repository.UserRepository;
import com.kashif.smart_habit_tracker.security.UserPrincipal;
import com.kashif.smart_habit_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    private User getCurrentUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null)
            throw new ResourceNotFoundException("User not logged in");
        return user;
    }

    @Override
    public UserResponse getUser() {
        User user = getCurrentUser();
        return UserMapper.userToUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(UserRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User with given id not found"));
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        return UserMapper.userToUserResponse(user);
    }

    @Override
    public void deleteUser() {
        User user = getCurrentUser();
        userRepository.delete(user);
    }
}
