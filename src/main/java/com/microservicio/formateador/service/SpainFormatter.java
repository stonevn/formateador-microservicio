package com.microservicio.formateador.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;

@Component("ES")
@Primary
public class SpainFormatter implements Formatter {

    @Override
    public CountryAmountFormatResult format(FormateadorRequest request) {
        BigDecimal amount = request.getAmount();
        DecimalFormat decimalFormat = getDecimalFormat(new Locale("es", "ES"));

        String formattedAmount = decimalFormat.format(amount.setScale(2, RoundingMode.CEILING));
        String currencySymbol = "€"; 
        
        return new CountryAmountFormatResult("ES", formattedAmount, currencySymbol);
    }

    private DecimalFormat getDecimalFormat(Locale locale) {
        DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance(locale);
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        return decimalFormat;
    }

    
    @Override
    public List<Country> getAvailableCountries() {
        List<Country> availableCountries = new ArrayList<>();
        availableCountries.add(new Country("ES", "Spain"));
        return availableCountries;
    }

    @Override
    public CountryAmountFormatResult formatAmount(String countryCode, BigDecimal amount) {
        DecimalFormat decimalFormat = getDecimalFormat(new Locale("es", "ES"));
    
        String formattedAmount = decimalFormat.format(amount.setScale(2, RoundingMode.CEILING));
        String currencySymbol = "€"; 
    
        return new CountryAmountFormatResult(countryCode, formattedAmount, currencySymbol);
    }


}