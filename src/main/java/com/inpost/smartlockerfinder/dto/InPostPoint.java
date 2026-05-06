package com.inpost.smartlockerfinder.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InPostPoint(
        String name,
        String country,
        List<String> type,
        String status,
        Location location,

        @JsonProperty("opening_hours")
        String openingHours,

        Address address,

        @JsonProperty("address_details")
        AddressDetails addressDetails,

        List<String> functions,

        @JsonProperty("payment_available")
        boolean paymentAvailable,

        @JsonProperty("recommended_low_interest_box_machines_list")
        List<String> recommendedLowInterestBoxMachinesList,

        @JsonProperty("location_247")
        boolean location247,

        @JsonProperty("image_url")
        String imageUrl
) {
}