package com.moviebazaar.theatre.service.impl;

import com.moviebazaar.common.exception.BaseException;
import com.moviebazaar.common.pagination.PageRequestDto;
import com.moviebazaar.common.pagination.PageResponseDto;
import com.moviebazaar.common.pagination.PaginationUtil;
import com.moviebazaar.theatre.dto.ScreenRequest;
import com.moviebazaar.theatre.dto.ScreenResponse;
import com.moviebazaar.theatre.dto.TheatreRequest;
import com.moviebazaar.theatre.dto.TheatreResponse;
import com.moviebazaar.theatre.dto.mapper.ScreenMapper;
import com.moviebazaar.theatre.dto.mapper.TheatreMapper;
import com.moviebazaar.theatre.entity.Screen;
import com.moviebazaar.theatre.entity.Theatre;
import com.moviebazaar.theatre.repository.ScreenRepository;
import com.moviebazaar.theatre.repository.TheatreRepository;
import com.moviebazaar.theatre.service.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TheatreServiceImpl implements TheatreService {

    private final TheatreRepository theatreRepo;
    private final ScreenRepository screenRepo;

    @Override
    public TheatreResponse create(TheatreRequest request) {
        Theatre theatre = TheatreMapper.toEntity(request);
        return TheatreMapper.toResponse(theatreRepo.save(theatre));
    }

    @Override
    public TheatreResponse getById(Long id) {
        Theatre theatre = theatreRepo.findById(id)
                .orElseThrow(() -> new BaseException("Theatre not found", "NOT_FOUND"));

        return TheatreMapper.toResponse(theatre);
    }


    @Override
    public PageResponseDto<TheatreResponse> getAll(PageRequestDto pageRequest) {

        Pageable pageable = PageRequest.of(
                pageRequest.getPage(),
                pageRequest.getSize()
        );

        Page<Theatre> page = theatreRepo.findAll(pageable);

        List<TheatreResponse> content = page.getContent()
                .stream()
                .map(TheatreMapper::toResponse)
                .toList();

        return PaginationUtil.buildPageable(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    @Override
    public List<TheatreResponse> getByCity(String city) {
        return theatreRepo.findByCity(city)
                .stream()
                .map(TheatreMapper::toResponse)
                .toList();
    }

    @Override
    public ScreenResponse addScreen(ScreenRequest request) {

        Theatre theatre = theatreRepo.findById(request.getTheatreId())
                .orElseThrow(() -> new BaseException("Theatre not found", "NOT_FOUND"));

        Screen screen = new Screen();
        screen.setName(request.getName());
        screen.setTotalSeats(request.getTotalSeats());
        screen.setTheatre(theatre);

        return ScreenMapper.toResponse(screenRepo.save(screen));
    }
}