package com.StudentCourse.CustomExceptions;

public class AlreadyFoundException extends RuntimeException{
    public AlreadyFoundException(String message)
    {
        super(message);
    }
}
