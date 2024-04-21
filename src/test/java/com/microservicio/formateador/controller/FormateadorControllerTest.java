package com.microservicio.formateador.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.formateador.model.FormateadorRequest;
import com.microservicio.formateador.service.FormateadorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
public class FormateadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FormateadorService formateadorService;

    @InjectMocks
    private FormateadorController formateadorController;


    @Test
    public void testFormatAmount() throws Exception {
     
        BigDecimal amount = new BigDecimal("12345.123");
        String country = "España";

        String expectedResponse = "ES: 12.345,13 €";
        when(formateadorService.formatAmount(any(FormateadorRequest.class))).thenReturn(expectedResponse);

      
        mockMvc.perform(get("/formateador/" + country)
                .param("amount", amount.toString()) 
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetCountries() throws Exception {
        mockMvc.perform(get("/formateador/countries"))
                .andExpect(status().isOk());
    }

    // Método auxiliar para convertir objetos a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}