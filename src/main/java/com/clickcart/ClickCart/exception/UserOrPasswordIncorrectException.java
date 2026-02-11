package com.clickcart.ClickCart.exception;

public class UserOrPasswordIncorrectException extends RuntimeException{

    public UserOrPasswordIncorrectException(String message){
        super(message);
    }
}
