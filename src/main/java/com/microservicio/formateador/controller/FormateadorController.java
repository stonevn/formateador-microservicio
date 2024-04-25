package com.microservicio.formateador.controller;

import com.microservicio.formateador.exception.GlobalExceptionHandler;
import com.microservicio.formateador.model.ApiResponse;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.service.Formatter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/formateador")
@Api(value = "Formateador API", tags = "Formateador")
public class FormateadorController {

    @Autowired
    private Formatter formateadorService;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @GetMapping("/countries")
    @ApiOperation(value = "Get available countries", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<List<Country>>> getAvailableCountries() {
        try {
            List<Country> countries = formateadorService.getAvailableCountries();
            ApiResponse<List<Country>> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", countries);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return globalExceptionHandler.handleException(e);
        }
    }

    @GetMapping("/{countryCode}")
    @ApiOperation(value = "Format amount for given country", response = ApiResponse.class)
    public ResponseEntity<ApiResponse<CountryAmountFormatResult>> formatAmount(@PathVariable String countryCode, @RequestParam BigDecimal amount) {
        try {
            CountryAmountFormatResult result = formateadorService.formatAmount(countryCode, amount);
            ApiResponse<CountryAmountFormatResult> response = new ApiResponse<>(HttpStatus.OK.value(), "Success", result);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
             ApiResponse<CountryAmountFormatResult> response = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Bad Request", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            ApiResponse<CountryAmountFormatResult> response = new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}