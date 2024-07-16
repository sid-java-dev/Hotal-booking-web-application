package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Country;
import com.hotel_booking.entity.Location;
import com.hotel_booking.exception.LocationException;
import com.hotel_booking.repository.LocationRepository;
import com.hotel_booking.service.CountryService;
import com.hotel_booking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private CountryService countryService;

    @Override
    public Location saveLocation(Location location, Long countryId) {
        Country country = countryService.getCountryById(countryId);
        if (country != null) {
            location.setCountry(country);
            return locationRepository.save(location);
        }
        throw new LocationException("Invalid Location");
    }

    @Override
    public Location getLocation(Location location) {
        Country country = countryService.getCountryByName(location.getCountry());
        if(country!=null){
            return  locationRepository.findByLocationName(location.getLocationName());
        }else{
            throw  new LocationException("location not found ");
        }

    }
}
