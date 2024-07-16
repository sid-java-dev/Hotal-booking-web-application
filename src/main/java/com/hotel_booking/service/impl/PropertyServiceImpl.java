package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Amenities;
import com.hotel_booking.entity.Location;
import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyCategory;
import com.hotel_booking.exception.PropertyException;
import com.hotel_booking.payload.PropertyDto;
import com.hotel_booking.repository.PropertyRepository;
import com.hotel_booking.service.*;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private AmenitiesService amenitiesService;
    private PropertyCategoriesService categoriesService;
    private LocationService locationService;

    public PropertyServiceImpl(PropertyRepository propertyRepository, AmenitiesService amenitiesService, PropertyCategoriesService categoriesService, LocationService locationService) {
        this.propertyRepository = propertyRepository;
        this.amenitiesService = amenitiesService;
        this.categoriesService = categoriesService;
        this.locationService = locationService;
    }

    @Override
    public Property saveProperty(PropertyDto propertyDto) {
        List<Amenities> amenities = amenitiesService.getAmenities(propertyDto.getAmenities());
        PropertyCategory categoryForProperty = categoriesService.getCategoryForProperty(propertyDto.getPropertyCategory());
        Location location = locationService.getLocation(propertyDto.getLocation());
        if(!amenities.isEmpty() &&categoryForProperty!=null &&location!=null){
            Property p=new Property();
            p.setPropertyName(propertyDto.getPropertyName());
            p.setNoOfBathrooms(propertyDto.getNoOfBathrooms());
            p.setNoOfBedrooms(propertyDto.getNoOfBedrooms());
            p.setNoOfBeds(propertyDto.getNoOfBeds());
            p.setNightlyPrice(propertyDto.getNightlyPrice());
            p.setNoOfGuests(propertyDto.getNoOfGuests());
            p.setAmenities(amenities);
            p.setLocation(location);
            p.setPropertyCategory(categoryForProperty);
           return propertyRepository.save(p);
        }else throw new PropertyException(" property not found ");

    }
    @Override
    public Property getPropertyById(Long id) {
        return  propertyRepository.findById(id).orElseThrow(
                () -> new PropertyException("Property not found with id " + id)
        );
    }

    @Override
    public List<Property>getPropertyByPlace(String placeName) {
        List<Property> properties = propertyRepository.findPropertyByPlace(placeName);
        if(properties.isEmpty()){
            throw new PropertyException("Property not found!!");
        }
        return properties;
    }
}
