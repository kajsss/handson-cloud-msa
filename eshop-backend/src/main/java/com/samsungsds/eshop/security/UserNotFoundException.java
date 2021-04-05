package com.samsungsds.eshop.security;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String email){
        super(email + " NotFoundException");
    }

}