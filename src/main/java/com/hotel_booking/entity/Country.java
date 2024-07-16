package com.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "country_name", nullable = false, length = 150)
    private String countryName;

    @OneToMany(orphanRemoval = true)
    @JsonManagedReference
    @JoinColumn(name = "country_id")
    private Set<Location> locations = new LinkedHashSet<>();

}