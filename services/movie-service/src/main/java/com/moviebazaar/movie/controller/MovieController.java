package com.moviebazaar.movie.controller;

import com.moviebazaar.common.pagination.ApiResponse;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.movie.dto.MovieRequest;
import com.moviebazaar.movie.dto.MovieResponse;
import com.moviebazaar.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ApiResponse<MovieResponse> create(@RequestBody MovieRequest request) {
        return ApiResponse.success("Movie created", service.create(request));
    }

    @GetMapping("/{id}")
    public ApiResponse<MovieResponse> getById(@PathVariable Long id) {
        return ApiResponse.success("Success", service.getById(id));
    }

    @GetMapping
    public ApiResponse<PageResponseDto<MovieResponse>> getAll(PageRequestDto pageRequest) {
        return ApiResponse.success("Success", service.getAll(pageRequest));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("Deleted", null);
    }
}
