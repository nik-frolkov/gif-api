package com.nikfrolkov.gif.api.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExchangeRateGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExchangeRateErrorResponse> handlerException(RateExchangeException exception) {
        ExchangeRateErrorResponse response = new ExchangeRateErrorResponse(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
