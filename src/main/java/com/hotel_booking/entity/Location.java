package com.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "location_name", nullable = false, length = 200)
    private String locationName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    private Country country;

}
