package com.hotel_booking.controller;

import com.hotel_booking.entity.Country;
import com.hotel_booking.payload.CountryDto;
import com.hotel_booking.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto) {
            Country country = countryService.addCountry(countryDto);
            return new ResponseEntity<>(country, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {

            Country country = countryService.getCountryById(id);
            return new ResponseEntity<>(country, HttpStatus.OK);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
            Country country = countryService.updateCountry(id, countryDto);
            return new ResponseEntity<>(country, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
