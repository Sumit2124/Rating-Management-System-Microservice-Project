package com.project.hotel.service.service;

import com.project.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService
{
    public Hotel createhotel(Hotel hotel);
    public Hotel getHotel(String hotelId);
    public List<Hotel> getAllHotels();
}
