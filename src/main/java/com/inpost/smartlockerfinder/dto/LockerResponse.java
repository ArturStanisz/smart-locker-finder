package com.inpost.smartlockerfinder.dto;

import java.util.List;

public record LockerResponse(
        String name,
        String city,
        String province,
        String address,
        String status,
        boolean open247,
        boolean paymentAvailable,
        List<String> recommendedAlternatives,
        String imageUrl,
        int score,
        String reason
) {
}