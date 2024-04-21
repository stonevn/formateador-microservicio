package com.microservicio.formateador.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservicio.formateador.model.FormateadorRequest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FormateadorServiceTest {

    @Mock
    private FormateadorRequest request;

    @InjectMocks
    private FormateadorService formateadorService;


    @Test
    public void testFormatAmountSpain() {
        when(request.getAmount()).thenReturn(new BigDecimal("12345.123"));
        when(request.getCountry()).thenReturn("España");
        
        String formattedAmount = formateadorService.formatAmount(request);
        assertEquals("ES: 12.345,13 €", formattedAmount);
    }

    @Test
    public void testFormatAmountFrance() {
        when(request.getAmount()).thenReturn(new BigDecimal("12345.123"));
        when(request.getCountry()).thenReturn("Francia");
        
        String formattedAmount = formateadorService.formatAmount(request);
        assertEquals("FR: 12 345,13 €", formattedAmount);
    }

    @Test
    public void testFormatAmountUSA() {
        when(request.getAmount()).thenReturn(new BigDecimal("12345.123"));
        when(request.getCountry()).thenReturn("Estados Unidos");

        String formattedAmount = formateadorService.formatAmount(request);
        assertEquals("US: $12,345.12", formattedAmount);
    }
    
    @Test
    public void testGetAvailableCountries() {
        String[] expectedCountries = {"España", "Francia", "Estados Unidos"};
        String[] countries = formateadorService.getAvailableCountries();
        assertEquals(expectedCountries.length, countries.length);
        for (int i = 0; i < expectedCountries.length; i++) {
            assertEquals(expectedCountries[i], countries[i]);
        }
    }
}