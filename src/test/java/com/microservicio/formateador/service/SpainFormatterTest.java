package com.microservicio.formateador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class SpainFormatterTest {

    @InjectMocks
    private SpainFormatter spainFormatter;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testFormat() throws JsonProcessingException {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(12345.13);
        String countryCode = "ES";
        FormateadorRequest request = new FormateadorRequest(amount, countryCode);
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult(countryCode, "12.345,13 €", "€");
    
        // Act
        CountryAmountFormatResult actualResult = spainFormatter.format(request);
    
        // Assert
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void testGetAvailableCountries() throws JsonProcessingException {
        // Arrange
        List<Country> expectedCountries = List.of(new Country("ES", "Spain"));

        // Act
        List<Country> actualCountries = spainFormatter.getAvailableCountries();

        // Assert
        assertEquals(objectMapper.writeValueAsString(expectedCountries), objectMapper.writeValueAsString(actualCountries));
    }

    @Test
    public void testFormatAmount() throws JsonProcessingException {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(12345.13);
        String countryCode = "ES";
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult(countryCode, "12.345,13 €", "€");

        // Act
        CountryAmountFormatResult actualResult = spainFormatter.formatAmount(countryCode, amount);

        // Assert
        assertEquals(objectMapper.writeValueAsString(expectedResult), objectMapper.writeValueAsString(actualResult));
    }
}