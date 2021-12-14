package com.vacacoin.ordersAndCartms.exceptions;

public class BuyNotFoundException extends RuntimeException{
    public BuyNotFoundException(String message){
        super(message);
    }
}
