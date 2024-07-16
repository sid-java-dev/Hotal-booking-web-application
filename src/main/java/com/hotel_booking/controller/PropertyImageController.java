package com.hotel_booking.controller;

import com.hotel_booking.entity.PropertyImage;
import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.exception.ImageException;
import com.hotel_booking.service.PropertyImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/propertyImage")
public class PropertyImageController {

    private final PropertyImageService propertyImageService;

    public PropertyImageController(PropertyImageService propertyImageService) {
        this.propertyImageService = propertyImageService;
    }

    @PostMapping(path = "/add/{propertyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropertyImage> uploadPropertyImage(@RequestPart("file") MultipartFile file,
                                                             @PathVariable long propertyId
    ) {
        try {
            return new ResponseEntity<>(propertyImageService.savePropertyImage(file, propertyId), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ImageException("Failed to upload Images");
        }
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<List<PropertyImage>> getPropertyImage(@PathVariable long propertyId) {
        return new ResponseEntity<>(propertyImageService.getAllImageForProperty(propertyId), HttpStatus.OK);
    }
}
