package com.vacacoin.ordersAndCartms.exceptions;

public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(String message){
        super(message);
    }
}
