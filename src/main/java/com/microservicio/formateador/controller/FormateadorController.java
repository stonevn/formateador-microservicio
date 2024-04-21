package com.microservicio.formateador.controller;

import com.microservicio.formateador.model.FormateadorRequest;
import com.microservicio.formateador.service.FormateadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/formateador")
@Api(value = "Formateador API", tags = "Formateador")
public class FormateadorController {

    @Autowired
    private FormateadorService formateadorService;

    @GetMapping("/{country}")
    @ApiOperation(value = "Format amount for given country", response = String.class)
    public String formatAmount(@PathVariable String country, @RequestParam BigDecimal amount) {
        FormateadorRequest request = new FormateadorRequest();
        request.setCountry(country);
        request.setAmount(amount);
        return formateadorService.formatAmount(request);
    }

    @GetMapping("/countries")
    @ApiOperation(value = "Get available countries", response = String[].class)
    public String[] getAvailableCountries() {
        return formateadorService.getAvailableCountries();
    }
}