package com.example.bookreview.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LocationDto {
    Long locationId;
    BigDecimal lat;
    BigDecimal lng;

    public LocationDto(Long locationId, BigDecimal lat, BigDecimal lng) {
        this.locationId = locationId;
        this.lat = lat;
        this.lng = lng;
    }
}
