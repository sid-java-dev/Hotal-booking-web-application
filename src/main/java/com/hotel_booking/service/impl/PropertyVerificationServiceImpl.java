package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Property;
import com.hotel_booking.exception.PropertyException;
import com.hotel_booking.payload.PropertyDto;
import com.hotel_booking.repository.PropertyRepository;
import com.hotel_booking.service.PropertyService;
import com.hotel_booking.service.PropertyVerificationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyVerificationServiceImpl implements PropertyVerificationService {

    private PropertyService propertyService;
    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;

    public PropertyVerificationServiceImpl(PropertyService propertyService, ModelMapper modelMapper, PropertyRepository propertyRepository) {
        this.propertyService = propertyService;
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto verifyProperty(Long id) {
        Property property = propertyService.getPropertyById(id);
        if(property.isPropertyValid()){
            throw new PropertyException("Property is already verified");
        }
        property.setPropertyValid(true);
        propertyRepository.save(property);
        return mapToDto(property);
    }
    public PropertyDto mapToDto(Property property){
        return modelMapper.map(property,PropertyDto.class);
    }
}
