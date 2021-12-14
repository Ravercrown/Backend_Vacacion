package com.vacacoin.ordersAndCartms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class PaymentMethodNotFoundAdvise {
    @ResponseBody
    @ExceptionHandler(PaymentMethodNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String EntityNotFoundAdvise (PaymentMethodNotFoundException e){
        return e.getMessage();
    }
}
