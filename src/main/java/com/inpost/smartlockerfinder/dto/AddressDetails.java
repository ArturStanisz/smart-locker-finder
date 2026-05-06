package com.inpost.smartlockerfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AddressDetails(
        @JsonProperty("city")
        String city,

        @JsonProperty("province")
        String province,

        @JsonProperty("post_code")
        String postCode,

        @JsonProperty("street")
        String street,

        @JsonProperty("building_number")
        String buildingNumber
) {
}