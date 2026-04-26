package com.moviebazaar.auth.communication;

import com.moviebazaar.common.dto.user.UserRequest;
import com.moviebazaar.common.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8081/api/v1")
public interface UserServiceClient {
    @PostMapping("/users")
    UserResponse createUser(@RequestBody UserRequest request);

    @GetMapping("/users/email/{email}")
    UserResponse getByUsername(@PathVariable String email);
}

