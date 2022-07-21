package com.example.bookreview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@ToString
public class Location {
    @Id
    @Column(name="LOCATION_ID")
    @SequenceGenerator(name = "LOCATION_ID_GENERATOR", sequenceName = "LOCATION_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_ID_GENERATOR")
    private Long locationId;
    private BigDecimal lat;
    private BigDecimal lng;

    public Location() {

    }

    public Location(BigDecimal lat, BigDecimal lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
