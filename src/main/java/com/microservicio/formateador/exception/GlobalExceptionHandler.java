package com.microservicio.formateador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.microservicio.formateador.model.ApiResponse;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<List<Country>>> handleException(Exception ex) {
        ApiResponse<List<Country>> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<CountryAmountFormatResult>> handleIllegalArgumentException(IllegalArgumentException ex) {
         ApiResponse<CountryAmountFormatResult> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad Request", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ApiResponse<?> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<ApiResponse<List<Country>>> handleCountryNotFoundException(CountryNotFoundException ex) {
        ApiResponse<List<Country>> response = new ApiResponse<>(HttpStatus.NOT_FOUND.value(), ex.getMessage(), Collections.emptyList());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}