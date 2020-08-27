package com.denisenko.airlineticketsshop.service.exception;

public class InvalidObjectFactoryException extends Exception {

    private String errorCode;
    private String field;

    public InvalidObjectFactoryException(String errorCode, String field) {
        super(errorCode + " & " + field);
        this.errorCode = errorCode;
        this.field = field;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
