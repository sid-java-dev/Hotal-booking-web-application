package com.hotel_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "amenities")
@AllArgsConstructor
@NoArgsConstructor
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amenity_name", nullable = false, length = 100)
    private String amenityName;
}

