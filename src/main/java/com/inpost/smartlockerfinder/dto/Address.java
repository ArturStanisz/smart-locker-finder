package com.inpost.smartlockerfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Address(
        String line1,
        String line2
) {
}