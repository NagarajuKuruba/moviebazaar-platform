package com.moviebazaar.show.service.impl;

import com.moviebazaar.show.dto.ShowRequest;
import com.moviebazaar.show.dto.ShowResponse;
import com.moviebazaar.show.dto.mapper.ShowMapper;
import com.moviebazaar.show.entiry.Show;
import com.moviebazaar.show.repository.ShowRepository;
import com.moviebazaar.show.service.ShowService;
import com.moviebazaar.show.service.client.MovieClient;
import com.moviebazaar.show.service.client.TheatreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository repo;
    private final MovieClient movieClient;
    private final TheatreClient theatreClient;

    @Override
    public ShowResponse create(ShowRequest request) {

        Show show = ShowMapper.toEntity(request);
        show = repo.save(show);

        return buildResponse(show);
    }

    @Override
    public List<ShowResponse> getShowsByMovieAndDate(Long movieId, LocalDate date) {

        List<Show> shows = repo.findByMovieIdAndShowDate(movieId, date);

        return shows.stream()
                .map(this::buildResponse)
                .toList();
    }

    private ShowResponse buildResponse(Show show) {

        // 🔗 Fetch movie
        var movie = movieClient.getById(show.getMovieId()).getData();

        // 🔗 Fetch theatre
        var theatre = theatreClient.getById(show.getTheatreId()).getData();

        return ShowResponse.builder()
                .id(show.getId())
                .movieId(show.getMovieId())
                .movieName(movie.getTitle())
                .theatreId(show.getTheatreId())
                .theatreName(theatre.getName())
                .city(theatre.getCity())
                .screenId(show.getScreenId())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .price(show.getPrice())
                .build();
    }
}