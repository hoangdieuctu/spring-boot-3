package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicatedUserException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(UserRequest userRequest) {
        makeSureUserDoesNotExist(userRequest.username());
        var user = save(userRequest);
        return toResponse(user);
    }

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private User save(UserRequest userRequest) {
        var user = new User();
        user.setUsername(userRequest.username());
        user.setFullName(userRequest.fullName());
        return userRepository.save(user);
    }

    private void makeSureUserDoesNotExist(String username) {
        userRepository
                .findByUsername(username)
                .ifPresent(user -> {
                    throw new DuplicatedUserException(username);
                });
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getFullName());
    }
}
