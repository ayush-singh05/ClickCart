package com.clickcart.ClickCart.exception;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException(String message){
        super(message);
    }
}
