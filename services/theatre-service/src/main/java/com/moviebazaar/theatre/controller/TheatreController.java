package com.moviebazaar.theatre.controller;

import com.moviebazaar.common.pagination.ApiResponse;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.theatre.dto.ScreenRequest;
import com.moviebazaar.theatre.dto.ScreenResponse;
import com.moviebazaar.theatre.dto.TheatreRequest;
import com.moviebazaar.theatre.dto.TheatreResponse;
import com.moviebazaar.theatre.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theatres")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService service;

    @PostMapping
    public ApiResponse<TheatreResponse> create(@RequestBody TheatreRequest request) {
        return ApiResponse.success("Created", service.create(request));
    }

    @GetMapping
    public ApiResponse<PageResponseDto<TheatreResponse>> getAll(PageRequestDto pageRequest) {
        return ApiResponse.success("Success", service.getAll(pageRequest));
    }
    @GetMapping("{id}")
    public ApiResponse<TheatreResponse> getTheatreById(@PathVariable Long id) {
        return ApiResponse.success("Success", service.getById(id));
    }

    @GetMapping("/city/{city}")
    public ApiResponse<List<TheatreResponse>> getByCity(@PathVariable String city) {
        return ApiResponse.success("Success", service.getByCity(city));
    }

    @PostMapping("/screens")
    public ApiResponse<ScreenResponse> addScreen(@RequestBody ScreenRequest request) {
        return ApiResponse.success("Screen added", service.addScreen(request));
    }
}
