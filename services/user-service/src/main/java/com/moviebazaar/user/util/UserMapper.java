package com.moviebazaar.user.util;


import com.moviebazaar.user.dto.UserRequest;
import com.moviebazaar.user.dto.UserResponse;
import com.moviebazaar.user.entity.User;

public class UserMapper {

    public static User toEntity(UserRequest req) {
        return User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .phone(req.getPhone())
                .role("ROLE_USER")
                .build();
    }

    public static UserResponse toResponse(User user) {
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setName(user.getName());
        res.setEmail(user.getEmail());
        res.setPhone(user.getPhone());
        res.setRole(user.getRole());
        return res;
    }
}
