package com.hotel_booking.controller;

import com.hotel_booking.entity.Location;
import com.hotel_booking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/add/{countryId}")
    public ResponseEntity<Location>saveLocation(@RequestBody Location location, @PathVariable Long countryId){
        return  new ResponseEntity<>(locationService.saveLocation(location,countryId), HttpStatus.CREATED);
    }
}
