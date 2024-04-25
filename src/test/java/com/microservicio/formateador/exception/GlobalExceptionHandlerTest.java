package com.microservicio.formateador.exception;

import com.microservicio.formateador.model.ApiResponse;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleException() {
        // Arrange
        Exception ex = new Exception("Internal server error");

        // Act
        ResponseEntity<ApiResponse<List<Country>>> responseEntity = globalExceptionHandler.handleException(ex);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

        ApiResponse<List<Country>> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), responseBody.getStatus());
        assertEquals("Internal server error", responseBody.getMessage());
    }

    @Test
    public void testHandleIllegalArgumentException() {
        // Arrange
        IllegalArgumentException ex = new IllegalArgumentException("Bad request");

        // Act
        ResponseEntity<ApiResponse<CountryAmountFormatResult>> responseEntity = globalExceptionHandler.handleIllegalArgumentException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ApiResponse<CountryAmountFormatResult> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("Bad Request", responseBody.getMessage());
    }

    @Test
    public void testHandleNoHandlerFoundException() {
        // Arrange
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/endpoint", null);

        // Act
        ResponseEntity<ApiResponse<?>> responseEntity = globalExceptionHandler.handleNoHandlerFoundException(ex);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        ApiResponse<?> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("No handler found for GET /endpoint", responseBody.getMessage());
    }

    @Test
    public void testHandleCountryNotFoundException() {
        // Arrange
        CountryNotFoundException ex = new CountryNotFoundException("Country not found");
    
        // Act
        ResponseEntity<ApiResponse<List<Country>>> responseEntity = globalExceptionHandler.handleCountryNotFoundException(ex);
    
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    
        ApiResponse<List<Country>> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("Country not found", responseBody.getMessage());
        assertEquals(Collections.emptyList(), responseBody.getData());
    }
}