package com.project.rating.service.service.impl;

import com.project.rating.service.entity.Rating;
import com.project.rating.service.exceptions.RatingNotFoundException;
import com.project.rating.service.repository.RatingRepository;
import com.project.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImpl implements RatingService {
   @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating createRating(Rating savedrating) {
        Rating rating = ratingRepository.save(savedrating);
        return rating;
    }

    @Override
    public Rating getRating(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new RatingNotFoundException("No rating has been found with this rating id"));
        return rating;
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        List<Rating> ratings = ratingRepository.findByUserId(userId).get();
        return ratings;
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId).get();
    }
}
