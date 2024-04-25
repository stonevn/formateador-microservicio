package com.microservicio.formateador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
public class USFormatterTest {

    @InjectMocks
    private USFormatter usFormatter;


    @Test
    public void testFormat() throws JsonProcessingException {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(12345.12);
        String countryCode = "US";
        FormateadorRequest request = new FormateadorRequest(amount, countryCode);
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult(countryCode, "$12,345.12", "$");

        // Act
        CountryAmountFormatResult actualResult = usFormatter.format(request);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testGetAvailableCountries() throws JsonProcessingException {
        // Arrange
        List<Country> expectedCountries = List.of(new Country("US", "United States"));

        // Act
        List<Country> actualCountries = usFormatter.getAvailableCountries();

        // Assert
        assertEquals(expectedCountries, actualCountries);
    }

    @Test
    public void testFormatAmount() throws JsonProcessingException {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(12345.12);
        String countryCode = "US";
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult(countryCode, "$12,345.12", "$");

        // Act
        CountryAmountFormatResult actualResult = usFormatter.formatAmount(countryCode, amount);

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}