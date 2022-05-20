package com.StudentCourse.CustomExceptions;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String message)
    {
        super(message);
    }
}
