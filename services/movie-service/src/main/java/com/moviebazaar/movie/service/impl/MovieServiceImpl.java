package com.moviebazaar.movie.service.impl;

import com.moviebazaar.common.exception.BaseException;
import com.moviebazaar.common.exception.Constants;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.common.pagination.PaginationUtil;
import com.moviebazaar.movie.dto.MovieRequest;
import com.moviebazaar.movie.dto.MovieResponse;
import com.moviebazaar.movie.dto.mapper.MovieMapper;
import com.moviebazaar.movie.entity.Movie;
import com.moviebazaar.movie.repository.MovieRepository;
import com.moviebazaar.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;

    @Override
    public MovieResponse create(MovieRequest request) {
        Movie movie = MovieMapper.toEntity(request);
        return MovieMapper.toResponse(repo.save(movie));
    }

    @Override
    public MovieResponse getById(Long id) {
        Movie movie = repo.findById(id)
                .orElseThrow(() -> new BaseException("Movie not found", Constants.RESOURCE_NOT_FOUND));

        return MovieMapper.toResponse(movie);
    }

    @Override
    public PageResponseDto<MovieResponse> getAll(PageRequestDto pageRequest) {

        Pageable pageable = PageRequest.of(
                pageRequest.getPage(),
                pageRequest.getSize()
        );

        Page<Movie> page = repo.findAll(pageable);

        List<MovieResponse> content = page.getContent()
                .stream()
                .map(MovieMapper::toResponse)
                .toList();

        return PaginationUtil.buildPageable(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    @Override
    public void delete(Long id) {
        repo.findById(id)
                .orElseThrow(() -> new BaseException("Movie not found", Constants.RESOURCE_NOT_FOUND));

        repo.deleteById(id);
    }
}