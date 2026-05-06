package com.inpost.smartlockerfinder.service;

import com.inpost.smartlockerfinder.dto.InPostPoint;
import org.springframework.stereotype.Service;

@Service
public class LockerScoringService {

    public int calculateScore(InPostPoint point) {
        int score = 0;

        if ("Operating".equals(point.status())) {
            score += 40;
        }

        if (point.location247()) {
            score += 30;
        }

        if (point.paymentAvailable()) {
            score += 20;
        }

        if (point.recommendedLowInterestBoxMachinesList() != null
                && !point.recommendedLowInterestBoxMachinesList().isEmpty()) {
            score += 10;
        }

        return score;
    }

    public String buildReason(InPostPoint point) {
        StringBuilder reason = new StringBuilder();

        if ("Operating".equals(point.status())) {
            reason.append("Paczkomat działa. ");
        }

        if (point.location247()) {
            reason.append("Jest dostępny 24/7. ");
        }

        if (point.paymentAvailable()) {
            reason.append("Obsługuje płatności. ");
        }

        if (point.recommendedLowInterestBoxMachinesList() != null
                && !point.recommendedLowInterestBoxMachinesList().isEmpty()) {
            reason.append("API InPost wskazuje alternatywne paczkomaty w pobliżu.");
        }

        return reason.toString().trim();
    }
}