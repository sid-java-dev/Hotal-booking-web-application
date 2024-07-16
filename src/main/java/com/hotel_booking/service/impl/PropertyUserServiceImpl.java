package com.hotel_booking.service.impl;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.entity.UserRole;
import com.hotel_booking.exception.PropertyException;
import com.hotel_booking.exception.UserException;
import com.hotel_booking.payload.PropertyUserDto;
import com.hotel_booking.payload.SearchUserDto;
import com.hotel_booking.repository.PropertyRepository;
import com.hotel_booking.repository.PropertyUserRepository;
import com.hotel_booking.service.PropertyUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyUserServiceImpl implements PropertyUserService {

    private PropertyUserRepository userRepository;
    private ModelMapper modelMapper;


    public PropertyUserServiceImpl(PropertyUserRepository userRepository, ModelMapper modelMapper
                                  ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }
    @Override
    public PropertyUser saveUser(PropertyUserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserException("username is already taken!!");
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserException("Email is already taken!!");
        }
        if (userRepository.existsByMobile(userDto.getMobile())) {
            throw new UserException("Mobile is already taken!!!");
        }
        PropertyUser user = mapToEntity(userDto);
        user.setUserRole(UserRole.ROLE_USER);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(5)));
        userRepository.save(user);
        return null;
    }
    @Override
    public void deleteUserById(PropertyUser propertyUser, long id) {
        if (propertyUser.getId() == id || propertyUser.getUserRole() == UserRole.ROLE_ADMIN) {

            if (userRepository.existsById(propertyUser.getId()) || userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new UserException("user not found with given id!!");
            }
        } else {
            throw new UserException("Unauthorized to delete user");
        }
    }

    @Override
    public SearchUserDto getUserByUserId(PropertyUser propertyUser, long userId) {
        if (propertyUser.getId() == userId || propertyUser.getUserRole() == UserRole.ROLE_ADMIN) {
            PropertyUser user = userRepository.findById(userId).orElseThrow(
                    () -> new UserException("User not found with id: " + userId)
            );
            if (user != null) {
                return mapToDto(user);
            } else {
                throw new UserException("User not found with id:" + userId);
            }
        }
        throw new UserException("Unauthorized to read user Details");
    }

    @Override
    public List<SearchUserDto> getAllUser(PropertyUser propertyUser) {
        List<PropertyUser> users = userRepository.findAll();
         return users.stream().map(user -> mapToDto(user)).collect(Collectors.toList());


    }
    @Override
    public SearchUserDto updateUser(PropertyUser propertyUser, PropertyUserDto propertyUserDto) {
        PropertyUser user = userRepository.findById(propertyUser.getId()).orElseThrow(
                () -> new UserException("User not found with id: " + propertyUser.getUsername())
        );
        PropertyUser u = mapToEntity(propertyUserDto);
        user.setFirstName(u.getFirstName());
        user.setLastName(u.getLastName());
        user.setUsername(u.getUsername());
        user.setEmail(u.getEmail());
        user.setMobile(u.getMobile());
        PropertyUser savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public PropertyUser findPropertyUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                ()->new PropertyException("Property not found with username: "+username));
    }


    PropertyUser mapToEntity(PropertyUserDto userDto) {
        return modelMapper.map(userDto, PropertyUser.class);
    }
    SearchUserDto mapToDto(PropertyUser user) {
        return modelMapper.map(user, SearchUserDto.class);
    }

}
