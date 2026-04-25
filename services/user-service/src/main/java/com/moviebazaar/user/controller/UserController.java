package com.moviebazaar.user.controller;

import com.moviebazaar.common.pagination.ApiResponse;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.user.dto.UserRequest;
import com.moviebazaar.user.dto.UserResponse;
import com.moviebazaar.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody UserRequest request) {

        UserResponse user = service.create(request);

        return ApiResponse.success("User created successfully", user);
    }
    @GetMapping("/{id}")
    public ApiResponse<UserResponse> get(@PathVariable Long id) {

        return ApiResponse.success("User fetched", service.getById(id));
    }
    @GetMapping
    public ApiResponse<PageResponseDto<UserResponse>> list(PageRequestDto request) {

        PageResponseDto<UserResponse> users = service.getAll(request);

        return ApiResponse.success("Users fetched successfully", users);
    }
    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,
                               @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}