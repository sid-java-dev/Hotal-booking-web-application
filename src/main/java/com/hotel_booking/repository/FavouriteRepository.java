package com.hotel_booking.repository;

import com.hotel_booking.entity.Favourite;
import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    @Query("select f FROM Favourite f WHERE f.property=:property AND f.propertyUser=:propertyUser")
    Optional<Favourite> findFavourite(@Param("property") Property property, @Param("propertyUser") PropertyUser propertyUser);

    @Query("SELECT f FROM Favourite f WHERE f.isFavourite = true AND f.propertyUser = :propertyUser")
    List<Favourite> findUserFavouriteProperty(@Param("propertyUser") PropertyUser propertyUser);
}