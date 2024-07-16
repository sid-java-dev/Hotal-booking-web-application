package com.hotel_booking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_images")
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "image_name", nullable = false)
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "property_id")
    @JsonIgnore
    private Property property;
}
