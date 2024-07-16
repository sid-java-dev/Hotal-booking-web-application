package com.hotel_booking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String tokenType="Bearer";
    private  String token;
    public JwtResponse(String token) {
        this.token=token;
    }
}
