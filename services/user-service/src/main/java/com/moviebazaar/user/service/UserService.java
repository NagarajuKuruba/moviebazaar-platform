package com.moviebazaar.user.service;


import com.moviebazaar.common.dto.user.UserRequest;
import com.moviebazaar.common.dto.user.UserResponse;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.common.pagination.PaginationUtil;

import com.moviebazaar.user.entity.User;
import com.moviebazaar.user.repository.UserRepository;
import com.moviebazaar.user.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public UserResponse getByEmail(String email) {
        return repo.findByEmail(email)
                .map(UserMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public PageResponseDto<UserResponse> getAll(PageRequestDto request) {
        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize(),
                Sort.by("id").descending()
        );

        Page<User> page = repo.findAll(pageable);

        return PaginationUtil.buildPageable(
                page.getContent().stream().map(UserMapper::toResponse).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
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