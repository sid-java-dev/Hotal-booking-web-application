package com.hotel_booking.repository;

import com.hotel_booking.entity.PropertyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PropertyCategoryRepository extends JpaRepository<PropertyCategory, Long> {
    @Query("select pc from PropertyCategory pc where pc=:propertyCategory")
    Optional<PropertyCategory> findPropertyCategory(@Param("propertyCategory")PropertyCategory propertyCategory);
}