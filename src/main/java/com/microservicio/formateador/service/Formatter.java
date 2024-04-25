package com.microservicio.formateador.service;

import java.math.BigDecimal;
import java.util.List;

import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;

public interface Formatter {

    List<Country> getAvailableCountries();
    CountryAmountFormatResult format(FormateadorRequest request);
    CountryAmountFormatResult formatAmount(String countryCode, BigDecimal amount);

}