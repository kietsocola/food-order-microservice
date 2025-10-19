package com.foodordermicroservice.presentation.dto;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueResponse {
    private String id;
    private String name;
    private String location;
    private List<ProductResponse> products;
}
