package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyImage;
import com.hotel_booking.exception.PropertyException;
import com.hotel_booking.repository.PropertyImageRepository;
import com.hotel_booking.service.BucketService;
import com.hotel_booking.service.PropertyImageService;
import com.hotel_booking.service.PropertyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PropertyImageServiceImpl implements PropertyImageService {

    private PropertyImageRepository imageRepository;
    private PropertyService propertyService;
    private BucketService bucketService;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    public PropertyImageServiceImpl(PropertyImageRepository imageRepository, PropertyService propertyService, BucketService bucketService) {
        this.imageRepository = imageRepository;
        this.propertyService = propertyService;
        this.bucketService = bucketService;
    }

    @Override
    public PropertyImage savePropertyImage(MultipartFile file, long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        if(property!=null){
           String url=bucketService.uploadFile(file, bucketName);
            PropertyImage image=new PropertyImage();
            image.setImageUrl(url);
            image.setImageName(file.getOriginalFilename());
            image.setProperty(property);
           return  imageRepository.save(image);
        }else{
            throw new PropertyException("property not found!!");
        }

    }

    @Override
    public List<PropertyImage> getAllImageForProperty(long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        if(property!=null){
            return imageRepository.getAllPropertyByPropertyId(propertyId);

        }else{
            throw new PropertyException("Property not found!!");
        }
    }


}
