package com.moviebazaar.show.service.client;

import com.moviebazaar.common.dto.user.movie.MovieResponse;
import com.moviebazaar.common.pagination.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", url = "http://localhost:8083")
public interface MovieClient {

    @GetMapping("/api/v1/movies/{id}")
    ApiResponse<MovieResponse> getById(@PathVariable Long id);
}
