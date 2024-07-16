package com.hotel_booking.service;

import com.hotel_booking.entity.Location;

public interface LocationService {
    Location saveLocation(Location location,Long countryId);

    Location getLocation(Location location);
}
