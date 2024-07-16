package com.hotel_booking.service;

import com.hotel_booking.entity.PropertyImage;
import com.hotel_booking.entity.PropertyUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyImageService {
    PropertyImage savePropertyImage(MultipartFile file, long propertyId);
    List<PropertyImage> getAllImageForProperty(long propertyId);


}
