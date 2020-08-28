package com.denisenko.airlineticketsshop.service.exception.database;

public class ConnectionNotSetException extends RuntimeException {

    public ConnectionNotSetException(String message, Throwable e){
        super(message, e);
    }
}
