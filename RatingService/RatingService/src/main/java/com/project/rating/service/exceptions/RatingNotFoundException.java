package com.project.rating.service.exceptions;

public class RatingNotFoundException extends RuntimeException
{
    public RatingNotFoundException()
    {
        super("No rating has been found please check the rating id once again");
    }
    public RatingNotFoundException(String msg)
    {
        super(msg);
    }

}
