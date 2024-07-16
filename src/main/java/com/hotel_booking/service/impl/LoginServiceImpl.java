package com.hotel_booking.service.impl;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.exception.UserException;
import com.hotel_booking.payload.LoginDto;
import com.hotel_booking.repository.PropertyUserRepository;
import com.hotel_booking.security.JwtTokenService;
import com.hotel_booking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private PropertyUserRepository userRepository;
    @Autowired
    private JwtTokenService tokenService;

    @Override
    public String authenticateUser(LoginDto loginDto) {
        PropertyUser user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(
                () -> new UserException("User not found with : " + loginDto.getUsername())
        );
        if (user != null && BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user);
        }
        else{
            throw new UserException("Invalid password!!");
        }
    }
}
