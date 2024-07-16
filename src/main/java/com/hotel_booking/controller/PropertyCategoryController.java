package com.hotel_booking.controller;

import com.hotel_booking.entity.PropertyCategory;
import com.hotel_booking.payload.CategoryDto;
import com.hotel_booking.service.PropertyCategoriesService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class PropertyCategoryController {
    @Autowired
    private PropertyCategoriesService propertyCategoriesService;

    @PostMapping("/add")
    public ResponseEntity<List<PropertyCategory>> createPropertyCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(propertyCategoriesService.addCategories(categoryDto), HttpStatus.CREATED);
    }
}
