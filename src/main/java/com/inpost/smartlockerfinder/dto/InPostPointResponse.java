package com.inpost.smartlockerfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InPostPointResponse(
        int count,
        int page,

        @JsonProperty("per_page")
        int perPage,

        @JsonProperty("total_pages")
        int totalPages,

        List<InPostPoint> items
) {
}