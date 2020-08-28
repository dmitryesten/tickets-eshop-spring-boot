package com.denisenko.airlineticketsshop.service.exception;

public class CookieNotFoundException extends RuntimeException {

    public CookieNotFoundException(){
        super();
    }

    public CookieNotFoundException(String message){
        super(message);
    }

}
