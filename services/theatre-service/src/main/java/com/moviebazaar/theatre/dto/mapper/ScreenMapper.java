package com.moviebazaar.theatre.dto.mapper;

import com.moviebazaar.theatre.dto.ScreenResponse;
import com.moviebazaar.theatre.entity.Screen;

public class ScreenMapper {

    public static ScreenResponse toResponse(Screen s) {
        return ScreenResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .totalSeats(s.getTotalSeats())
                .theatreId(s.getTheatre().getId())
                .build();
    }
}