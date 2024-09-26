package com.project.rating.service.repository;

import com.project.rating.service.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating,String>
{
Optional<List<Rating>> findByUserId(String userId);
Optional<List<Rating>> findByHotelId(String hotelId);
}
