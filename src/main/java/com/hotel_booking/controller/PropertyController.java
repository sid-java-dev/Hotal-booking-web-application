package com.hotel_booking.controller;

import com.hotel_booking.entity.Property;
import com.hotel_booking.payload.PropertyDto;
import com.hotel_booking.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<Property> createProperty(@RequestBody PropertyDto propertyDto){
    return new ResponseEntity<>(propertyService.saveProperty(propertyDto), HttpStatus.CREATED);
    }

}
