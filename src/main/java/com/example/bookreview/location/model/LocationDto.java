package com.example.bookreview.location.model;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class LocationDto {
    Long locationId;
    BigDecimal lat;
    BigDecimal lng;

}
