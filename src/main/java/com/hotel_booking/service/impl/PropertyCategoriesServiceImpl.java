package com.hotel_booking.service.impl;

import com.hotel_booking.entity.PropertyCategory;
import com.hotel_booking.exception.PropertyCategoryException;
import com.hotel_booking.payload.CategoryDto;
import com.hotel_booking.repository.PropertyCategoryRepository;
import com.hotel_booking.service.PropertyCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyCategoriesServiceImpl implements PropertyCategoriesService {
    @Autowired
    private PropertyCategoryRepository categoryRepository;
    @Override
    public List<PropertyCategory> addCategories(CategoryDto categoryDto) {
        List<String> categories = categoryDto.getCategories();
        List<PropertyCategory> categoryList = categories.stream().map(category -> {
            PropertyCategory propertyCategory = new PropertyCategory();
            propertyCategory.setCategoryName(category);
            return propertyCategory;
        }).collect(Collectors.toList());
        return categoryRepository.saveAll(categoryList);

    }

    @Override
    public PropertyCategory getCategoryForProperty(PropertyCategory propertyCategory) {
        Optional<PropertyCategory> opCategory = categoryRepository.findPropertyCategory(propertyCategory);
       if(!opCategory.isPresent()){
           throw new PropertyCategoryException("Property not found with category");
       }
       return opCategory.get();
    }
}
