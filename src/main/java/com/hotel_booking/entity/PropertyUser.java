package com.hotel_booking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_user")
public class PropertyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "mobile", nullable = false, length = 100)
    private String mobile;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Column(name = "username", nullable = false, length = 150)
    private String username;

    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;

    @Column(name = "first_name", nullable = false, length = 150)
    private String firstName;

}
