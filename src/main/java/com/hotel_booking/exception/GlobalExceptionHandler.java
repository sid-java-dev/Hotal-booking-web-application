package com.hotel_booking.exception;

import com.hotel_booking.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CountryException.class)
    public ResponseEntity<ErrorDetails> handleCountryException(
            CountryException e, WebRequest webRequest
    ) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage(), LocalDate.now(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
