package com.hotel_booking.controller;


import com.hotel_booking.payload.SearchPropertyDto;
import com.hotel_booking.service.PropertySearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-property")
public class PropertySearchController {
    private PropertySearchService searchService;

    public PropertySearchController(PropertySearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<SearchPropertyDto>searchPropertyById(@PathVariable long propertyId){
        return new ResponseEntity<>(searchService.getPropertyById(propertyId), HttpStatus.OK);
    }
    @GetMapping("/place/{placeName}")
    public ResponseEntity<List<SearchPropertyDto>> findProperty(@PathVariable String placeName) {
        List<SearchPropertyDto> searchPropertyDtos = searchService.findPropertyByPlace(placeName);
        return new ResponseEntity<>(searchPropertyDtos, HttpStatus.OK);
    }

}
