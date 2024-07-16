package com.hotel_booking.repository;

import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.property = :property AND r.propertyUser = :propertyUser")
    Optional<Review> findReviewByUser(@Param("property") Property property, @Param("propertyUser") PropertyUser propertyUser);

    List<Review> findByPropertyUserId(Long userId);

    @Query("SELECT r FROM Review r WHERE r.property = :property")
    List<Review> findReviewForProperty(@Param("property") Property property);
}