package com.hotel_booking.service;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.payload.PropertyUserDto;
import com.hotel_booking.payload.SearchUserDto;

import java.util.List;
import java.util.Optional;

public interface PropertyUserService  {
    PropertyUser saveUser(PropertyUserDto userDto);

    void deleteUserById(PropertyUser propertyUser, long userId);

    SearchUserDto getUserByUserId(PropertyUser propertyUser, long userId);
    List<SearchUserDto> getAllUser(PropertyUser propertyUser);

    SearchUserDto updateUser(PropertyUser propertyUser, PropertyUserDto propertyUserDto);

   PropertyUser findPropertyUserByUsername(String username);
}
