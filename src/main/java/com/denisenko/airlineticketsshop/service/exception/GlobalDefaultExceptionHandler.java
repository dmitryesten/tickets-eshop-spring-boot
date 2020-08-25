package com.denisenko.airlineticketsshop.service.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorCustomTemplate> customHandleNotFound(Exception ex) {
        StringBuilder stringBuilder = new StringBuilder(ex.getMessage());

        String errorCode = stringBuilder.substring(0, stringBuilder.indexOf("&")).trim();
        String message = stringBuilder.substring(stringBuilder.indexOf("&") + 1, stringBuilder.length()).trim(); //ex.getMessage().substring(ex.getMessage().indexOf("&")  + 1, ex.getMessage().length() - 1);

        ErrorCustomTemplate errors = new ErrorCustomTemplate(errorCode, ex.getStackTrace()[0].toString(), message);

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

}
