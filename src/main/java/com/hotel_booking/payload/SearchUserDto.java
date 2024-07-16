package com.hotel_booking.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserDto {
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private String password;
}
