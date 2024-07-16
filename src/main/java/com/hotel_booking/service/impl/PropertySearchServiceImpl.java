package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyImage;
import com.hotel_booking.payload.SearchPropertyDto;
import com.hotel_booking.service.PropertyImageService;
import com.hotel_booking.service.PropertySearchService;
import com.hotel_booking.service.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertySearchServiceImpl implements PropertySearchService {

    private PropertyService propertyService;
    private PropertyImageService imageService;

    public PropertySearchServiceImpl(PropertyService propertyService, PropertyImageService imageService) {
        this.propertyService = propertyService;
        this.imageService = imageService;
    }

    @Override
    public SearchPropertyDto getPropertyById(long propertyId) {
       Property property = propertyService.getPropertyById(propertyId);
       if (property != null) {
           List<PropertyImage> imageList = imageService.getAllImageForProperty(propertyId);
           SearchPropertyDto search = new SearchPropertyDto();
           search.setProperty(property);
           search.setImageList(imageList);
           return search;
       }
        return null;
    }

    @Override
    public List<SearchPropertyDto> findPropertyByPlace(String placeName) {
        List<Property> properties = propertyService.getPropertyByPlace(placeName);
        List<SearchPropertyDto> propertyList = new ArrayList<>();

        for (Property property : properties) {
            List<PropertyImage> imageList = imageService.getAllImageForProperty(property.getId());
            SearchPropertyDto dto = new SearchPropertyDto(); // Create a new dto for each iteration
            dto.setProperty(property);
            dto.setImageList(imageList);
            propertyList.add(dto);
        }

        return propertyList;
    }


}

