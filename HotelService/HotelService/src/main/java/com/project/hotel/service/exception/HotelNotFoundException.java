package com.project.hotel.service.exception;

public class HotelNotFoundException extends RuntimeException
{
    public HotelNotFoundException()
    {
        super("No Hotel has been associated with this id");
    }
    public HotelNotFoundException(String msg)
    {
        super(msg);
    }
}
