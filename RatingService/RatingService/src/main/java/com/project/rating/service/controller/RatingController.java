package com.project.rating.service.controller;

import com.project.rating.service.entity.Rating;
import com.project.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Rating> createRating(@RequestBody Rating savedrating) {
        Rating rating = ratingService.createRating(savedrating);
        return new ResponseEntity(rating, HttpStatus.CREATED);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRating(@PathVariable String ratingId) {
        Rating rating = ratingService.getRating(ratingId);
        return new ResponseEntity<>(rating, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        List<Rating> ratings = ratingService.getRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PreAuthorize(" hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId) {
        return new ResponseEntity<>(ratingService.getRatingByHotelId(hotelId), HttpStatus.OK);
    }
}
