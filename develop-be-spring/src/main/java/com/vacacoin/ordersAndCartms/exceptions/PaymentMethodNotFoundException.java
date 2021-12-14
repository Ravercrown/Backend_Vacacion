package com.vacacoin.ordersAndCartms.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException{
    public PaymentMethodNotFoundException(String message){
        super(message);
    }
}
