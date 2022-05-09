package com.ivana;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //then springboot will go to 404 page
public class MyNotFoundException extends RuntimeException{
    public MyNotFoundException() {
    }

    public MyNotFoundException(String message) {
        super(message);
    }

    public MyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
