package com.moviebazaar.user.service;


import com.moviebazaar.user.dto.UserRequest;
import com.moviebazaar.user.dto.UserResponse;
import com.moviebazaar.user.entity.User;
import com.moviebazaar.user.repository.UserRepository;
import com.moviebazaar.user.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    public UserResponse create(UserRequest request) {
        User user = UserMapper.toEntity(request);
        return UserMapper.toResponse(repo.save(user));
    }

    public UserResponse getById(Long id) {
        return repo.findById(id)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<UserResponse> getAll() {
        return repo.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = repo.findById(id).orElseThrow();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        return UserMapper.toResponse(repo.save(user));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}