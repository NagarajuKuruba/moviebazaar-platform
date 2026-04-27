package com.moviebazaar.common.dto.user.theatre;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TheatreResponse {
    private Long id;
    private String name;
    private String city;
}
