package com.microservicio.formateador.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Excepción lanzada cuando hay errores de validación.")
public class ValidationException extends RuntimeException {

    @ApiModelProperty(value = "Mensaje de error detallado.")
    private String message;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}