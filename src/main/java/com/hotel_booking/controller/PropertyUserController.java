package com.hotel_booking.controller;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.payload.PropertyUserDto;
import com.hotel_booking.payload.SearchUserDto;
import com.hotel_booking.service.PropertyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class PropertyUserController {
    @Autowired
    private PropertyUserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody PropertyUserDto userDto){
        userService.saveUser(userDto);
        return new ResponseEntity<>("User Registration successful", HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@AuthenticationPrincipal PropertyUser propertyUser,
                                 @PathVariable long userId){
        userService.deleteUserById(propertyUser,userId);
        return "User Deleted Success!!";
    }
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<?>getUserById(@AuthenticationPrincipal PropertyUser propertyUser,
                                        @PathVariable long userId ){
        SearchUserDto searchDto = userService.getUserByUserId(propertyUser,userId);
        return new ResponseEntity<>(searchDto,HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<SearchUserDto>>getAllUser(@AuthenticationPrincipal PropertyUser propertyUser){
        List<SearchUserDto> userDtos=userService.getAllUser(propertyUser);
        return new ResponseEntity<>(userDtos,HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<SearchUserDto>updateUser(@AuthenticationPrincipal PropertyUser propertyUser,
                                                   @RequestBody PropertyUserDto propertyUserDto){
        SearchUserDto updatedUser=userService.updateUser(propertyUser,propertyUserDto);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }
}
