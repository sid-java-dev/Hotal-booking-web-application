package com.hotel_booking.controller;

import com.hotel_booking.entity.PropertyUser;
import com.hotel_booking.entity.Review;
import com.hotel_booking.payload.ResponseReviewDto;
import com.hotel_booking.payload.ReviewDto;
import com.hotel_booking.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add/{propertyId}")
    public ResponseEntity<?> addReview(@PathVariable long propertyId,
                                       @RequestBody ReviewDto reviewDto,
                                       @AuthenticationPrincipal PropertyUser propertyUser) {
        if (propertyUser != null) {
            return new ResponseEntity<>(reviewService.addReview(propertyId, reviewDto, propertyUser), HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("Please Login again !!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<Review> getAllReviewsForCurrentUser(@AuthenticationPrincipal PropertyUser user) {
        return reviewService.getReviewGivenByUser(user.getId());

    }
    @GetMapping("/{propertyId}")
    public ResponseEntity<ResponseReviewDto>getAllReviewForProperty(@PathVariable Long propertyId){
        return new ResponseEntity<>(reviewService.getAllReviewsForProperty(propertyId),HttpStatus.OK);
    }
}
