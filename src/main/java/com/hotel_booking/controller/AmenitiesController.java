package com.hotel_booking.controller;

import com.hotel_booking.entity.Amenities;
import com.hotel_booking.payload.AmenitiesDto;
import com.hotel_booking.service.AmenitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/amenities")
public class AmenitiesController {
    @Autowired
    private AmenitiesService amenitiesService;
    @PostMapping("/add")
    public ResponseEntity<List<Amenities>> addAmenities(@RequestBody AmenitiesDto amenityDto){
        List<Amenities> amenitiesList = amenitiesService.addAmenities(amenityDto);
        return new ResponseEntity<>(amenitiesList, HttpStatus.CREATED);
    }
}
