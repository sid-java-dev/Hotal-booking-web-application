package com.hotel_booking.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyUserDto  {
    private String mobile;
    private String email;
    private String password;
    private String username;
    private String lastName;
    private String firstName;
}
