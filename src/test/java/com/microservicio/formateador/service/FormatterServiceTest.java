package com.microservicio.formateador.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.microservicio.formateador.exception.CountryNotFoundException;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FormatterServiceTest {

    @Mock
    private Formatter usFormatter;

    @Mock
    private Formatter franceFormatter;

    @Mock
    private Formatter spainFormatter;

    @InjectMocks
    private FormateadorServiceImpl formateadorService;

    @BeforeEach
    public void setup() {
        Map<String, Formatter> formatterMap = new HashMap<>();
        formatterMap.put("US", usFormatter);
        formatterMap.put("FR", franceFormatter);
        formatterMap.put("ES", spainFormatter);
        formateadorService = new FormateadorServiceImpl(formatterMap);
    }

    @Test
    public void testFormat_Success() {
        // Arrange
        FormateadorRequest request = new FormateadorRequest(BigDecimal.valueOf(1234.56), "ES");
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult("ES", "1.234,56 €", "€");
        when(spainFormatter.format(request)).thenReturn(expectedResult);

        // Act
        CountryAmountFormatResult actualResult = formateadorService.format(request);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFormat_CountryNotFoundException() {
        // Arrange
        FormateadorRequest request = new FormateadorRequest(BigDecimal.valueOf(1234.56), "UK");
        lenient().when(spainFormatter.format(request)).thenThrow(new CountryNotFoundException("No se encontró el país con el código: UK"));
    
        // Act & Assert
        assertThrows(CountryNotFoundException.class, () -> {
            formateadorService.format(request);
        });
    }

    @Test
    public void testFormatAmount_Success() {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(1234.56);
        CountryAmountFormatResult expectedResult = new CountryAmountFormatResult("ES", "1.234,56 €", "€");
        when(spainFormatter.formatAmount("ES", amount)).thenReturn(expectedResult);

        // Act
        CountryAmountFormatResult actualResult = formateadorService.formatAmount("ES", amount);

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testFormatAmount_CountryNotFoundException() {
        // Arrange
        BigDecimal amount = BigDecimal.valueOf(1234.56);
        lenient().when(spainFormatter.formatAmount("UK", amount)).thenThrow(new CountryNotFoundException("No se encontró el país con el código: UK"));
    
        // Act & Assert
        assertThrows(CountryNotFoundException.class, () -> {
            formateadorService.formatAmount("UK", amount);
        });
    }

    @Test
    public void testGetAvailableCountries() {
        // Arrange
        List<Country> expectedCountries = new ArrayList<>();
        expectedCountries.add(new Country("US", "United States"));
        expectedCountries.add(new Country("FR", "France"));
        expectedCountries.add(new Country("ES", "Spain"));
    
        when(usFormatter.getAvailableCountries()).thenReturn(List.of(new Country("US", "United States")));
        when(franceFormatter.getAvailableCountries()).thenReturn(List.of(new Country("FR", "France")));
        when(spainFormatter.getAvailableCountries()).thenReturn(List.of(new Country("ES", "Spain")));
    
        // Act
        List<Country> actualCountries = formateadorService.getAvailableCountries();
    
        // Assert
        assertThat(actualCountries).containsExactlyInAnyOrderElementsOf(expectedCountries);
    }
}