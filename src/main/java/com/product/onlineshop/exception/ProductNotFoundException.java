package com.product.onlineshop.exception;

public class ProductNotFoundException extends Exception{
    private final String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message=message;
    }
}
