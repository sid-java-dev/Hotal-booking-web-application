package com.hotel_booking.controller;

import com.hotel_booking.payload.PropertyDto;
import com.hotel_booking.service.PropertyVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties/")
public class PropertyVerificationController {
    private PropertyVerificationService propertyVerificationService;

    public PropertyVerificationController(PropertyVerificationService propertyVerificationService) {
        this.propertyVerificationService = propertyVerificationService;
    }
    @PatchMapping("/{id}/verify")
    public ResponseEntity<PropertyDto>verifyProperty(@PathVariable Long id){
        return new ResponseEntity<>(propertyVerificationService.verifyProperty(id), HttpStatus.OK);

    }
}
