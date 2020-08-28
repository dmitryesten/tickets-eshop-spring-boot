package com.denisenko.airlineticketsshop.service.exception.database;

public class CrudOperationNotExecuteException extends RuntimeException {

    public CrudOperationNotExecuteException(String message, Throwable e){
        super(message, e);
    }

}
