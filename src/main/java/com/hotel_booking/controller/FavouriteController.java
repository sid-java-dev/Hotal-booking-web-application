package com.hotel_booking.controller;

import com.hotel_booking.entity.Favourite;
import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.service.FavouriteService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favourite")
public class FavouriteController {
    private FavouriteService favouriteService;

    public FavouriteController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }

    @PostMapping("/property/{propertyId}")
    public ResponseEntity<String> addFavourite(@PathVariable long propertyId,
                                               @AuthenticationPrincipal PropertyUser propertyUser) {
        if (propertyUser != null) {
            favouriteService.addFavourite(propertyId, propertyUser);
            return new ResponseEntity<>("Property add successful as favourite", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not authenticated", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/properties")
    public List<Favourite> getAllFavourites (@AuthenticationPrincipal PropertyUser propertyUser) {
        return favouriteService.getAllUserFavouritesProperty(propertyUser);
    }
}
