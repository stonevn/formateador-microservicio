package com.microservicio.formateador.service;import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.microservicio.formateador.exception.CountryNotFoundException;
import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;

@Service
public class FormateadorServiceImpl implements Formatter {

    private final Map<String, Formatter> formatters;

    public FormateadorServiceImpl(Map<String, Formatter> formatters) {
        this.formatters = formatters;
    }

    @Override
    public CountryAmountFormatResult format(FormateadorRequest request) {
        String countryCode = request.getCountryCode();
        Formatter formatter = formatters.get(countryCode);
        if (formatter == null) {
            throw new CountryNotFoundException("No se encontró el país con el código: " + countryCode);
        }
        return formatter.format(request);
    }

    @Override
    public List<Country> getAvailableCountries() {
        // Utilizar una sola instancia de ArrayList para evitar crear nuevas instancias en cada llamada
        List<Country> availableCountries = new ArrayList<>();
        for (Formatter formatter : formatters.values()) {
            availableCountries.addAll(formatter.getAvailableCountries());
        }
        return availableCountries;
    }

    @Override
    public CountryAmountFormatResult formatAmount(String countryCode, BigDecimal amount) {
        Formatter formatter = formatters.get(countryCode);
        if (formatter == null) {
            throw new CountryNotFoundException("No se encontró el país con el código: " + countryCode);
        }
        return formatter.formatAmount(countryCode, amount);
    }
}