package com.moviebazaar.movie.dto;

import lombok.Data;

@Data
public class MovieRequest {
    private String title;
    private String language;
    private String genre;
    private Integer duration;
}