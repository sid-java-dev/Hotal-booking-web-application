package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Country;
import com.hotel_booking.exception.CountryException;
import com.hotel_booking.payload.CountryDto;
import com.hotel_booking.repository.CountryRepository;
import com.hotel_booking.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;


    @Override
    public Country addCountry(CountryDto countryDto)  {
        Optional<Country> optionalCountry = countryRepository.findByCountryName(countryDto.getCountryName());
        if (optionalCountry.isPresent()) {
            throw new CountryException("Country already exists");
        }
        Country country = new Country();
        country.setCountryName(countryDto.getCountryName());
        return countryRepository.save(country);
    }
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(
                ()->new CountryException("Country not found"));
    }

    @Override
    public Country updateCountry(Long id, CountryDto countryDto) {
        Country country = getCountryById(id);
        country.setCountryName(countryDto.getCountryName());
        return countryRepository.save(country);
    }

    @Override
    public void deleteCountry(Long id) {
        Country countryById = getCountryById(id);
        if(countryById==null){
            throw new CountryException("Country is already deleted");
        }
        countryRepository.deleteById(id);
    }

    @Override
    public Country getCountryByName(Country country) {
        Country country1 = countryRepository.findByCountryName(country.getCountryName()).orElseThrow(
                () -> new CountryException("Country not found with :" + country.getCountryName())
        );
        return country1;
    }

}
