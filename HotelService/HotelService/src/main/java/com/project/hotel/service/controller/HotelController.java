package com.project.hotel.service.controller;

import com.project.hotel.service.entity.Hotel;
import com.project.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    HotelService hotelService;
   @PostMapping
   @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel savehotel)
    {
        Hotel hotel = hotelService.createhotel(savehotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId)
    {
        Hotel hotel = hotelService.getHotel(hotelId);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize(" hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels,HttpStatus.OK);
    }
}
