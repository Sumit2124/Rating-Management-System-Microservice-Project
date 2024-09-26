package com.project.rating.service.service;

import com.project.rating.service.entity.Rating;

import java.util.List;

public interface RatingService
{
    public Rating createRating(Rating rating);
    public Rating getRating(String ratingId);
    public List<Rating> getRatings();
    List<Rating> getRatingByUserId(String userId);
    List<Rating> getRatingByHotelId(String hotelId);
}
