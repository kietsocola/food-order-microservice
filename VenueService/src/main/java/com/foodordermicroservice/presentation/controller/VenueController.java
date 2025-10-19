package com.foodordermicroservice.presentation.controller;

import com.foodordermicroservice.application.port.VenueUseCasePort;
import com.foodordermicroservice.domain.valueobject.VenueRequest;
import com.foodordermicroservice.presentation.dto.VenueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueUseCasePort venueUseCasePort;
    @GetMapping
    public ResponseEntity<List<VenueResponse>> getAllVenues() {
        List<VenueResponse> venues = venueUseCasePort.getAllVenues();
        return ResponseEntity.ok(venues);
    }
    @PostMapping
    public ResponseEntity<VenueResponse> createVenue(@RequestBody VenueRequest venueRequest) {
        VenueResponse venueResponse = venueUseCasePort.saveVenue(venueRequest);
        return ResponseEntity.ok(venueResponse);
    }
}
