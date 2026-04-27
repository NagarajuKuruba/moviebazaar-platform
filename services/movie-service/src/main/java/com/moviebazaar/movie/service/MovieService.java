package com.moviebazaar.movie.service;

import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.movie.dto.MovieRequest;
import com.moviebazaar.movie.dto.MovieResponse;

public interface MovieService {

    MovieResponse create(MovieRequest request);

    MovieResponse getById(Long id);

    PageResponseDto<MovieResponse> getAll(PageRequestDto pageRequest);

    void delete(Long id);
}
