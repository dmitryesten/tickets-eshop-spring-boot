package com.denisenko.airlineticketsshop.service.exception;


import java.util.HashMap;
import java.util.Map;

public class LoginNotFoundException extends Exception  {

    private String errorCode;
    private String field;

    public LoginNotFoundException(String errorCode, String field) {
        super(errorCode + " & " + field);
        this.errorCode = errorCode;
        this.field = field;
    }

    public String getErrorCode() {
        return errorCode;
    }

}
