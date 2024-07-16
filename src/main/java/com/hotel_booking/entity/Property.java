package com.hotel_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "property_name", nullable = false, length = 200)
    private String propertyName;

    @Column(name = "no_of_bathrooms")
    private Integer noOfBathrooms;
    @Column(name = "no_of_beds")
    private Integer noOfBeds;

    @Column(name = "no_of_bedrooms", nullable = false)
    private Integer noOfBedrooms;

    @Column(name = "no_of_guests", nullable = false)
    private Integer noOfGuests;

    @Column(name = "nightly_price", nullable = false)
    private Double nightlyPrice;

    @Column(name = "is_property_valid")
    private boolean isPropertyValid ;

    @ManyToOne
    @JoinColumn(name = "property_category_id")
    private PropertyCategory propertyCategory;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToMany
    @JoinTable(name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id"))
    private List<Amenities> amenities;
}