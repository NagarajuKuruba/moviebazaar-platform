package com.moviebazaar.show.controller;

import com.moviebazaar.common.pagination.ApiResponse;
import com.moviebazaar.show.dto.ShowRequest;
import com.moviebazaar.show.dto.ShowResponse;
import com.moviebazaar.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService service;

    @PostMapping
    public ApiResponse<ShowResponse> create(@RequestBody ShowRequest request) {
        return ApiResponse.success("Show created", service.create(request));
    }

    @GetMapping
    public ApiResponse<List<ShowResponse>> getShows(
            @RequestParam Long movieId,
            @RequestParam String date) {

        return ApiResponse.success(
                "Success",
                service.getShowsByMovieAndDate(
                        movieId,
                        LocalDate.parse(date)
                )
        );
    }
}