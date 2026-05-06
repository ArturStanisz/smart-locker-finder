package com.inpost.smartlockerfinder.controller;

import com.inpost.smartlockerfinder.dto.InPostPoint;
import com.inpost.smartlockerfinder.dto.LockerResponse;
import com.inpost.smartlockerfinder.service.InPostApiService;
import com.inpost.smartlockerfinder.service.LockerScoringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class LockerFinderController {

    private final InPostApiService inPostApiService;
    private final LockerScoringService lockerScoringService;

    public LockerFinderController(
            InPostApiService inPostApiService,
            LockerScoringService lockerScoringService
    ) {
        this.inPostApiService = inPostApiService;
        this.lockerScoringService = lockerScoringService;
    }

    @GetMapping("/api/lockers/find")
    public List<LockerResponse> findLockers(
            @RequestParam(defaultValue = "5") int limit
    ) {
        return inPostApiService.fetchPoints()
                .items()
                .stream()
                .filter(this::isOperatingParcelLocker)
                .limit(limit)
                .map(this::toLockerResponse)
                .toList();
    }

    @GetMapping("/api/lockers/city")
    public List<LockerResponse> findByCity(
            @RequestParam String city
    ) {
        return inPostApiService.fetchPoints()
                .items()
                .stream()
                .filter(this::isOperatingParcelLocker)
                .filter(point -> point.addressDetails() != null)
                .filter(point -> point.addressDetails().city() != null)
                .filter(point -> point.addressDetails().city().equalsIgnoreCase(city))
                .map(this::toLockerResponse)
                .toList();
    }

    @GetMapping("/api/lockers/city-province")
    public List<LockerResponse> findByCityAndProvince(
            @RequestParam String city,
            @RequestParam String province
    ) {
        return inPostApiService.fetchPoints()
                .items()
                .stream()
                .filter(this::isOperatingParcelLocker)
                .filter(point -> point.addressDetails() != null)
                .filter(point -> point.addressDetails().city() != null)
                .filter(point -> point.addressDetails().province() != null)
                .filter(point -> point.addressDetails().city().equalsIgnoreCase(city))
                .filter(point -> point.addressDetails().province().equalsIgnoreCase(province))
                .map(this::toLockerResponse)
                .toList();
    }

    @GetMapping("/api/lockers/alternatives")
    public List<LockerResponse> findAlternatives(
            @RequestParam String name
    ) {
        List<InPostPoint> points = inPostApiService.fetchPoints().items();

        InPostPoint selectedPoint = points.stream()
                .filter(point -> point.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (selectedPoint == null
                || selectedPoint.recommendedLowInterestBoxMachinesList() == null
                || selectedPoint.recommendedLowInterestBoxMachinesList().isEmpty()) {
            return Collections.emptyList();
        }

        List<String> recommendedNames = selectedPoint.recommendedLowInterestBoxMachinesList();

        return points.stream()
                .filter(this::isOperatingParcelLocker)
                .filter(point -> recommendedNames.contains(point.name()))
                .map(this::toLockerResponse)
                .toList();
    }

    private boolean isOperatingParcelLocker(InPostPoint point) {
        return "Operating".equals(point.status())
                && point.type() != null
                && point.type().contains("parcel_locker");
    }

    private LockerResponse toLockerResponse(InPostPoint point) {
        String city = point.addressDetails() != null ? point.addressDetails().city() : null;
        String province = point.addressDetails() != null ? point.addressDetails().province() : null;
        String address = buildAddress(point);

        List<String> recommendedAlternatives = point.recommendedLowInterestBoxMachinesList() != null
                ? point.recommendedLowInterestBoxMachinesList()
                : Collections.emptyList();

        return new LockerResponse(
                point.name(),
                city,
                province,
                address,
                point.status(),
                point.location247(),
                point.paymentAvailable(),
                recommendedAlternatives,
                point.imageUrl(),
                lockerScoringService.calculateScore(point),
                lockerScoringService.buildReason(point)
        );
    }

    private String buildAddress(InPostPoint point) {
        if (point.address() == null) {
            return null;
        }

        if (point.address().line1() != null && point.address().line2() != null) {
            return point.address().line1() + ", " + point.address().line2();
        }

        if (point.address().line1() != null) {
            return point.address().line1();
        }

        return point.address().line2();
    }
}