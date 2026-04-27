package com.moviebazaar.theatre.dto.mapper;

import com.moviebazaar.theatre.dto.TheatreRequest;
import com.moviebazaar.theatre.dto.TheatreResponse;
import com.moviebazaar.theatre.entity.Theatre;

public class TheatreMapper {

    public static Theatre toEntity(TheatreRequest req) {
        Theatre t = new Theatre();
        t.setName(req.getName());
        t.setCity(req.getCity());
        return t;
    }

    public static TheatreResponse toResponse(Theatre t) {
        return TheatreResponse.builder()
                .id(t.getId())
                .name(t.getName())
                .city(t.getCity())
                .build();
    }
}
