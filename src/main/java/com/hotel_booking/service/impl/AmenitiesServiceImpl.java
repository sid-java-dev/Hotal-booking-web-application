package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Amenities;
import com.hotel_booking.payload.AmenitiesDto;
import com.hotel_booking.repository.AmenitiesRepository;
import com.hotel_booking.service.AmenitiesService;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmenitiesServiceImpl implements AmenitiesService {

    private final AmenitiesRepository amenityRepo;
    @Autowired
    private ModelMapper modelMapper;
    public AmenitiesServiceImpl(AmenitiesRepository amenityRepo) {
        this.amenityRepo = amenityRepo;
    }
    @Override
    public List<Amenities> addAmenities(AmenitiesDto amenityDto) {
        List<String> amenityNames = amenityDto.getAmenityNames();
        if (amenityNames == null || amenityNames.isEmpty()) {
            throw new IllegalArgumentException("Amenity names cannot be null or empty");
        }

        List<Amenities> amenitiesList = amenityNames.stream()
                .filter(amenity -> !StringUtils.isEmpty(amenity)) // filter out empty strings
                .map(amenity -> {
                    Amenities a = new Amenities();
                    a.setAmenityName(amenity);
                    return a;
                })
                .collect(Collectors.toList());

        return amenityRepo.saveAll(amenitiesList);
    }

    @Override
    public List<Amenities> getAmenities(List<Amenities> amenities) {
        List<String> listOfAmenities = amenities.stream().map(a -> a.getAmenityName()).collect(Collectors.toList());
        return amenityRepo.findByAmenityName(listOfAmenities);
    }


}
