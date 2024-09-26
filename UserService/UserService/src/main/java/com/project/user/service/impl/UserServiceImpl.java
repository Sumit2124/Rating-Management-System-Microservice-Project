package com.project.user.service.impl;

import com.project.user.service.Repository.UserRepository;
import com.project.user.service.Services.UserService;
import com.project.user.service.entity.Hotel;
import com.project.user.service.entity.Rating;
import com.project.user.service.entity.User;
import com.project.user.service.exception.ResourceNotFoundException;
import com.project.user.service.external.services.HotelService;
import com.project.user.service.external.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    private Logger logger= LoggerFactory.getLogger(UserService.class);
    @Override
    public User saveUser(User saveUser) {
        String userid = UUID.randomUUID().toString();
        saveUser.setUserId(userid);
        User user = userRepository.save(saveUser);
        return user;
    }

    @Override
    public List<User> getAllUser() {

        List<User> users = userRepository.findAll();
        users=users.stream().map(user->{
            List<Rating> list = Arrays.asList(restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(), Rating[].class));
            list=list.stream().map(l->
            {
                ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + l.getHotelId(), Hotel.class);
                Hotel hotel = response.getBody();
                l.setHotel(hotel);
                return l;
            }).collect(Collectors.toList());
            user.setRatings(list);
            return user;
        }).collect(Collectors.toList());
        return  users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id " + userId + " is not found on resource server"));
        List<Rating> list = Arrays.asList(restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + userId, Rating[].class));
        list=list.stream().map(r->
        {
           // ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + r.getHotelId(), Hotel.class);
            //Hotel hotel = response.getBody();
            Hotel hotel=hotelService.getHotel(r.getHotelId());
            r.setHotel(hotel);
            return r;
        }).collect(Collectors.toList());
        user.setRatings(list);
        return user;
    }
}
