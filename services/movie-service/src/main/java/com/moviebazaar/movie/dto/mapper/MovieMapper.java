package com.moviebazaar.movie.dto.mapper;

import com.moviebazaar.movie.dto.MovieRequest;
import com.moviebazaar.movie.dto.MovieResponse;
import com.moviebazaar.movie.entity.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieRequest req) {
        Movie m = new Movie();
        m.setTitle(req.getTitle());
        m.setLanguage(req.getLanguage());
        m.setGenre(req.getGenre());
        m.setDuration(req.getDuration());
        return m;
    }

    public static MovieResponse toResponse(Movie m) {
        return MovieResponse.builder()
                .id(m.getId())
                .title(m.getTitle())
                .language(m.getLanguage())
                .genre(m.getGenre())
                .duration(m.getDuration())
                .build();
    }
}