package com.microservicio.formateador;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class FormateadorApplicationTest {

    @Test
    public void testMainMethod() {
        // Verificar que el método main se ejecuta sin lanzar excepciones
        assertDoesNotThrow(() -> FormateadorApplication.main(new String[] {}));
    }
}