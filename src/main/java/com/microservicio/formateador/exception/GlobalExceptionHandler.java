package com.microservicio.formateador.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request: La solicitud es inválida."),
            @ApiResponse(code = 500, message = "Internal Server Error: Se produjo un error interno en el servidor.")
    })
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error: Se produjo un error interno en el servidor.")
    })
    public ResponseEntity<String> handleGenericException(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }
}