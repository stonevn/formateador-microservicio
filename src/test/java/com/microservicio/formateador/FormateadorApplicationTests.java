package com.microservicio.formateador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(OutputCaptureExtension.class)

public class FormateadorApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        // Verificar que el contexto de la aplicación se cargue correctamente
    }

	

    @Test
    public void testFormatAmountEndpoint() {
        String response = restTemplate.getForObject("http://localhost:" + port + "/formateador/España?amount=12345.123", String.class);
        assertEquals("ES: 12.345,13 €", response);
    }

    @Test
    public void testGetAvailableCountriesEndpoint() {
        String[] response = restTemplate.getForObject("http://localhost:" + port + "/formateador/countries", String[].class);
        // Verificar que la lista de países disponibles es la esperada
        assertEquals(3, response.length);
        assertEquals("España", response[0]);
        assertEquals("Francia", response[1]);
        assertEquals("Estados Unidos", response[2]);
    }
}