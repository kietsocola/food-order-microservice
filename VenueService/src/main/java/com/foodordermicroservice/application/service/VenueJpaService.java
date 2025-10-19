package com.foodordermicroservice.application.service;

import com.foodordermicroservice.application.port.VenueJpaPort;
import com.foodordermicroservice.domain.valueobject.VenueRequest;
import com.foodordermicroservice.infrastructure.persistence.entity.VenueEntity;
import com.foodordermicroservice.infrastructure.persistence.mapper.VenueMapper;
import com.foodordermicroservice.infrastructure.persistence.repository.VenueJpaRepository;
import com.foodordermicroservice.presentation.dto.VenueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class VenueJpaService implements VenueJpaPort {
    private final VenueJpaRepository venueJpaRepository;

    @Override
    public List<VenueResponse> getAllVenues() {
        return venueJpaRepository.findAll().stream()
                .map(VenueMapper::toDto)
                .toList();
    }

    @Override
    public VenueResponse saveVenue(VenueRequest venueRequest) {
        var venueEntity = VenueMapper.toEntity(venueRequest);
        VenueEntity entity = venueJpaRepository.save(venueEntity);
        return VenueMapper.toDto(entity);
    }
}
