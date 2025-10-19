package com.foodordermicroservice.application.service;

import com.foodordermicroservice.application.port.VenueJpaPort;
import com.foodordermicroservice.application.port.VenueUseCasePort;
import com.foodordermicroservice.domain.valueobject.VenueRequest;
import com.foodordermicroservice.presentation.dto.VenueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueUseCase implements VenueUseCasePort {
    private final VenueJpaPort venueJpaPort;
    @Override
    public List<VenueResponse> getAllVenues() {
        return venueJpaPort.getAllVenues();
    }

    @Override
    public VenueResponse saveVenue(VenueRequest venueRequest) {
        return venueJpaPort.saveVenue(venueRequest);
    }
}
