package com.clickcart.ClickCart.exception;


public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message){
        super(message);
    }

}
