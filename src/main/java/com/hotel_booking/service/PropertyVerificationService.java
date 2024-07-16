package com.hotel_booking.service;

import com.hotel_booking.payload.PropertyDto;

public interface PropertyVerificationService {
    PropertyDto verifyProperty(Long id);
}
