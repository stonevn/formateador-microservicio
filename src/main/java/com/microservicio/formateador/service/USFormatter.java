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

@Component("US")
public class USFormatter implements Formatter {

    private static final String CURRENCY_SYMBOL = "$";

    @Override
    public CountryAmountFormatResult format(FormateadorRequest request) {
        BigDecimal amount = request.getAmount();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        String formattedAmount = CURRENCY_SYMBOL + decimalFormat.format(amount);
        return new CountryAmountFormatResult("US", formattedAmount, CURRENCY_SYMBOL);
    }
    @Override
    public List<Country> getAvailableCountries() {
        List<Country> availableCountries = new ArrayList<>();
        availableCountries.add(new Country("US", "United States"));
        return availableCountries;
    }

    @Override
    public CountryAmountFormatResult formatAmount(String countryCode, BigDecimal amount) {
    DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.US));
    decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

    String formattedAmount = "$" + decimalFormat.format(amount);
    String currencySymbol = "$"; 

    return new CountryAmountFormatResult(countryCode, formattedAmount, currencySymbol);
}
}