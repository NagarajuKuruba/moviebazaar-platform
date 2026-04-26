package com.moviebazaar.common.pagination;

public class PaginationUtil {

    public static <T> PageResponseDto<T> buildPageable(
            java.util.List<T> data,
            int page,
            int size,
            long totalElements
    ) {
        int totalPages = (int) Math.ceil((double) totalElements / size);

        return PageResponseDto.<T>builder()
                .data(data)
                .page(page)
                .size(size)
                .totalElements(totalElements)
                .totalPages(totalPages)
                .build();
    }
}