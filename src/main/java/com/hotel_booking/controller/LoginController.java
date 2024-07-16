package com.hotel_booking.controller;

import com.hotel_booking.payload.JwtResponse;
import com.hotel_booking.payload.LoginDto;
import com.hotel_booking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticUser(@RequestBody LoginDto loginDto) {
        String token = loginService.authenticateUser(loginDto);
        if (token != null) {
            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.OK);
    }

}
