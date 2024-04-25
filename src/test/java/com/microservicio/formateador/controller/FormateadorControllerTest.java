package com.microservicio.formateador.controller;

import com.microservicio.formateador.exception.GlobalExceptionHandler;
import com.microservicio.formateador.model.ApiResponse;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.service.Formatter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FormateadorControllerTest {

    @Mock
    private Formatter formatter;

    @Mock
    private GlobalExceptionHandler globalExceptionHandler;

    @InjectMocks
    private FormateadorController formateadorController;

    @Test
    public void testGetAvailableCountries_Success() {
        // Mock data
        List<Country> countries = new ArrayList<>();
        countries.add(new Country("US", "United States"));
        when(formatter.getAvailableCountries()).thenReturn(countries);

        // Call controller method
        ResponseEntity<ApiResponse<List<Country>>> response = formateadorController.getAvailableCountries();

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertTrue(response.getBody().getData() != null);
            assertEquals(countries, response.getBody().getData());
        } else {
            assertTrue(false, "Response body is null");
        }
    }

    @Test
    public void testGetAvailableCountries_Exception() {
        // Mock exception
        Exception exception = new RuntimeException("Error occurred");
        when(formatter.getAvailableCountries()).thenThrow(exception);
        when(globalExceptionHandler.handleException(exception)).thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

        // Call controller method
        ResponseEntity<ApiResponse<List<Country>>> response = formateadorController.getAvailableCountries();

        // Verify response
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testFormatAmount_Success() {
        // Mock data
        BigDecimal amount = BigDecimal.valueOf(100);
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult("US", "$100.00", "$");
        when(formatter.formatAmount("US", amount)).thenReturn(expectedResult);

        // Call controller method
        ResponseEntity<ApiResponse<CountryAmountFormatResult>> response = formateadorController.formatAmount("US", amount);

        // Verify response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        if (response.getBody() != null) {
            assertTrue(response.getBody().getData() != null);
            assertEquals(expectedResult, response.getBody().getData());
        } else {
            assertTrue(false, "Response body is null");
        }
    }


}