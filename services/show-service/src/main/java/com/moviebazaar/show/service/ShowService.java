package com.moviebazaar.show.service;

import com.moviebazaar.show.dto.ShowRequest;
import com.moviebazaar.show.dto.ShowResponse;

import java.time.LocalDate;
import java.util.List;

public interface ShowService {

    ShowResponse create(ShowRequest request);

    List<ShowResponse> getShowsByMovieAndDate(Long movieId, LocalDate date);
}