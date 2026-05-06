package com.inpost.smartlockerfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Location(
        double longitude,
        double latitude
) {
}