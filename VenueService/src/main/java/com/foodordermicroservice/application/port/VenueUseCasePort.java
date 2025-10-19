package com.foodordermicroservice.application.port;

import com.foodordermicroservice.domain.valueobject.VenueRequest;
import com.foodordermicroservice.presentation.dto.VenueResponse;

import java.util.List;

public interface VenueUseCasePort {
    List<VenueResponse> getAllVenues();
    VenueResponse saveVenue(VenueRequest venueRequest);
}
