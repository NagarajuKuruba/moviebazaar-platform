package com.moviebazaar.theatre.service;

import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.theatre.dto.ScreenRequest;
import com.moviebazaar.theatre.dto.ScreenResponse;
import com.moviebazaar.theatre.dto.TheatreRequest;
import com.moviebazaar.theatre.dto.TheatreResponse;

import java.util.List;

public interface TheatreService {

    TheatreResponse create(TheatreRequest request);

    PageResponseDto<TheatreResponse> getAll(PageRequestDto pageRequest);
    TheatreResponse getById(Long id);
    List<TheatreResponse> getByCity(String city);

    ScreenResponse addScreen(ScreenRequest request);
}