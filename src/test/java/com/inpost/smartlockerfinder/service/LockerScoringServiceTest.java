package com.inpost.smartlockerfinder.service;

import com.inpost.smartlockerfinder.dto.InPostPoint;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LockerScoringServiceTest {

    private final LockerScoringService lockerScoringService = new LockerScoringService();

    @Test
    void shouldReturnFullScoreForBestLocker() {
        InPostPoint point = new InPostPoint(
                "ADA01M",
                "PL",
                List.of("parcel_locker"),
                "Operating",
                null,
                "24/7",
                null,
                null,
                List.of("parcel_collect"),
                true,
                List.of("ADA01N"),
                true,
                null
        );

        int score = lockerScoringService.calculateScore(point);

        assertEquals(100, score);
    }

    @Test
    void shouldReturnLowerScoreWhenLockerHasNoAlternatives() {
        InPostPoint point = new InPostPoint(
                "ADK01M",
                "PL",
                List.of("parcel_locker"),
                "Operating",
                null,
                "24/7",
                null,
                null,
                List.of("parcel_collect"),
                true,
                null,
                true,
                null
        );

        int score = lockerScoringService.calculateScore(point);

        assertEquals(90, score);
    }

    @Test
    void shouldBuildReadableReason() {
        InPostPoint point = new InPostPoint(
                "ADA01M",
                "PL",
                List.of("parcel_locker"),
                "Operating",
                null,
                "24/7",
                null,
                null,
                List.of("parcel_collect"),
                true,
                List.of("ADA01N"),
                true,
                null
        );

        String reason = lockerScoringService.buildReason(point);

        assertEquals(
                "Paczkomat działa. Jest dostępny 24/7. Obsługuje płatności. API InPost wskazuje alternatywne paczkomaty w pobliżu.",
                reason
        );
    }
}