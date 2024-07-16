package com.hotel_booking.service;

import com.hotel_booking.payload.LoginDto;

public interface LoginService {
    String authenticateUser(LoginDto loginDto);
}
