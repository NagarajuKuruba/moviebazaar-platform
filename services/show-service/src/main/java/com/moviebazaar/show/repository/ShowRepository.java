package com.moviebazaar.show.repository;

import com.moviebazaar.show.entiry.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate date);

    List<Show> findByTheatreId(Long theatreId);
}