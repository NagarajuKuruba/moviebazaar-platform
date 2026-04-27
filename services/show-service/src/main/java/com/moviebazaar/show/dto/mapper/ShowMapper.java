package com.moviebazaar.show.dto.mapper;

import com.moviebazaar.show.dto.ShowRequest;
import com.moviebazaar.show.entiry.Show;

public class ShowMapper {

    public static Show toEntity(ShowRequest req) {
        Show s = new Show();
        s.setMovieId(req.getMovieId());
        s.setTheatreId(req.getTheatreId());
        s.setScreenId(req.getScreenId());
        s.setShowDate(req.getShowDate());
        s.setShowTime(req.getShowTime());
        s.setPrice(req.getPrice());
        return s;
    }
}