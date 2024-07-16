package com.hotel_booking.service;

import com.hotel_booking.entity.Country;
import com.hotel_booking.payload.CountryDto;

import java.util.List;

public interface CountryService {
    Country addCountry(CountryDto countryDto);
    List<Country> getAllCountries();

    Country getCountryById(Long id);

    Country updateCountry(Long id, CountryDto countryDto);

    void deleteCountry(Long id);

    Country getCountryByName(Country country);
}
