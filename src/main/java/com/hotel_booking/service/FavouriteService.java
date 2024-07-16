package com.hotel_booking.service;

import com.hotel_booking.entity.Favourite;
import com.hotel_booking.entity.PropertyUser;

import java.util.List;

public interface FavouriteService {
void addFavourite(Long propertyId, PropertyUser propertyUser);

    List<Favourite> getAllUserFavouritesProperty(PropertyUser propertyUser);
}
