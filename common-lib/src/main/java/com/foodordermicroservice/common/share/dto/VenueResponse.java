package com.foodordermicroservice.common.share.dto;

import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.common.share.enums.EntityStatus;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponse {
    private VenuesId venueId;
    private String venueName;
    private String description;
    private String venueAddress;
    private String venuePhone;
    private EntityStatus entityStatus;
}
