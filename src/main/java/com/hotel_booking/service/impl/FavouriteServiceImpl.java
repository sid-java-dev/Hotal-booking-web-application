package com.hotel_booking.service.impl;

import com.hotel_booking.entity.Favourite;
import com.hotel_booking.entity.Property;
import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.exception.FavouriteException;
import com.hotel_booking.repository.FavouriteRepository;
import com.hotel_booking.service.FavouriteService;
import com.hotel_booking.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavouriteServiceImpl implements FavouriteService {
    private final FavouriteRepository favouriteRepository;
    private PropertyService propertyService;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, PropertyService propertyService) {
        this.favouriteRepository = favouriteRepository;
        this.propertyService = propertyService;
    }

    @Override
    public void addFavourite(Long propertyId, PropertyUser propertyUser) {

        Property property = propertyService.getPropertyById(propertyId);
        if (property != null) {
            Optional<Favourite> optionalFavourite = favouriteRepository.findFavourite(property, propertyUser);
            if (optionalFavourite.isPresent()) {
                throw new FavouriteException("Property already added into favourite");
            }
            Favourite favourite = new Favourite();
            favourite.setFavourite(true);
            favourite.setProperty(property);
            favourite.setPropertyUser(propertyUser);
            favouriteRepository.save(favourite);
        }
    }

    @Override
    public List<Favourite> getAllUserFavouritesProperty(PropertyUser propertyUser) {
        List<Favourite> favourites = favouriteRepository.findUserFavouriteProperty(propertyUser);
        if (favourites.isEmpty()) {
            throw new FavouriteException("User doesn't hava any favourite Property");
        }
        return favourites;
    }
}

