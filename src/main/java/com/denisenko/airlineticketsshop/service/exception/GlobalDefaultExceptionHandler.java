package com.denisenko.airlineticketsshop.service.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> customHandleNotFound(Throwable ex) {
        StringBuilder stringBuilder = new StringBuilder(ex.getMessage());

        String errorCode = stringBuilder.substring(0, stringBuilder.indexOf("&")).trim();
        String message = stringBuilder.substring(stringBuilder.indexOf("&") + 1, stringBuilder.length()).trim(); //ex.getMessage().substring(ex.getMessage().indexOf("&")  + 1, ex.getMessage().length() - 1);

        ErrorResponse errors = new ErrorResponse(errorCode, ex.getStackTrace()[0].toString(), message);

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(MissingRequestHeaderException ex) {
        ErrorResponse errorCustomTemplate =
            new ErrorResponse("ENTITY_NOT_FOUND_EXCEPTION", ex.getStackTrace()[0].toString(), ex.getMessage());

        return new ResponseEntity<>(errorCustomTemplate, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CookieNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(CookieNotFoundException ex) {
        ErrorResponse errorCustomTemplate =
            new ErrorResponse("ENTITY_NOT_FOUND_EXCEPTION", ex.getStackTrace()[0].toString(), ex.getMessage());

        return new ResponseEntity<>(errorCustomTemplate, HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorResponse> details =
            ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    return new ErrorResponse("ERROR_VALIDATION_FIELD", error.getObjectName(), error.getDefaultMessage());
                })
                .collect(Collectors.toList());

        ErrorResponse error = new ErrorResponse("ERROR_VALIDATION_FIELD", ex.getStackTrace()[0].toString(), details.toString() );
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

}
