package com.microservicio.formateador.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.microservicio.formateador.model.Country;
import com.microservicio.formateador.model.CountryAmountFormatResult;
import com.microservicio.formateador.model.FormateadorRequest;

@Component("FR")
public class FranceFormatter implements Formatter {

    private static final String CURRENCY_SYMBOL = "€";

    @Override
    public CountryAmountFormatResult format(FormateadorRequest request) {
        BigDecimal amount = request.getAmount();
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.FRANCE);
        symbols.setCurrencySymbol(CURRENCY_SYMBOL);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');

        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        String formattedAmount = decimalFormat.format(amount);
        return new CountryAmountFormatResult("FR", formattedAmount, CURRENCY_SYMBOL);
    }

    @Override
    public List<Country> getAvailableCountries() {
        List<Country> availableCountries = new ArrayList<>();
        availableCountries.add(new Country("FR", "France"));
        return availableCountries;
    }

    @Override
    public CountryAmountFormatResult formatAmount(String countryCode, BigDecimal amount) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.FRANCE);
        symbols.setCurrencySymbol(CURRENCY_SYMBOL);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');
    
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
    
        String formattedAmount = decimalFormat.format(amount);
        return new CountryAmountFormatResult(countryCode, formattedAmount, CURRENCY_SYMBOL);
    }
}