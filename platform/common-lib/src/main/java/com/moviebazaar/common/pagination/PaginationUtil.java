package com.moviebazaar.common.pagination;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

public class PaginationUtil {

    public static Pageable buildPageable(PageRequestDto req) {

        Sort sort = req.getDirection().equalsIgnoreCase("desc") ?
                Sort.by(req.getSortBy()).descending() :
                Sort.by(req.getSortBy()).ascending();

        return PageRequest.of(req.getPage(), req.getSize(), sort);
    }

    public static <T, R> PageResponseDto<R> buildResponse(
            Page<T> page,
            java.util.function.Function<T, R> mapper) {

        return new PageResponseDto<>(
                page.getContent().stream().map(mapper).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}