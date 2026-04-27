package com.moviebazaar.show.service.client;

import com.moviebazaar.common.dto.user.theatre.TheatreResponse;
import com.moviebazaar.common.pagination.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "theatre-service",  url = "http://localhost:8084")
public interface TheatreClient {

    @GetMapping("/api/v1/theatres/{id}")
    ApiResponse<TheatreResponse> getById(@PathVariable Long id);
}