package com.hotel_booking.service;

import com.hotel_booking.entity.PropertyCategory;
import com.hotel_booking.payload.CategoryDto;

import java.util.List;

public interface PropertyCategoriesService {
    List<PropertyCategory> addCategories(CategoryDto categoryDto);

    PropertyCategory getCategoryForProperty(PropertyCategory propertyCategory);
}
