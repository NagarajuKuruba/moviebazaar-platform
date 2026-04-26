package com.moviebazaar.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
public class PageResponseDto<T> {

    private List<T> data;

    private int page;
    private int size;

    private long totalElements;
    private int totalPages;

    private boolean last;
}