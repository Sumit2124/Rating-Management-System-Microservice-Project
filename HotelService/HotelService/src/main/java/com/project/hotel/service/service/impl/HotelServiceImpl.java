package com.project.hotel.service.service.impl;

import com.project.hotel.service.entity.Hotel;
import com.project.hotel.service.exception.HotelNotFoundException;
import com.project.hotel.service.repository.HotelRepository;
import com.project.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
   @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createhotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new HotelNotFoundException("Hotel not found with this id:"+hotelId));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
