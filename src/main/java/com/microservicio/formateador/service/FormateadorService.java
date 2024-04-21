package com.microservicio.formateador.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.microservicio.formateador.model.FormateadorRequest;

@Service
public class FormateadorService {

    private static final String CURRENCY_SYMBOL = "€";
    private static final String FORMATO_US = "#,##0.00";

    public String formatAmount(FormateadorRequest request) {
        if (request == null || request.getAmount() == null || request.getCountry() == null) {
            throw new IllegalArgumentException("FormateadorRequest cannot be null and must have valid amount and country");
        }

        BigDecimal amount = request.getAmount();
        String country = request.getCountry();

        try {
            switch (country) {
                case "España":
                    return formatAmountSpain(amount);
                case "Francia":
                    return formatAmountFrance(amount);
                case "Estados Unidos":
                    return formatAmountUS(amount);
                default:
                    return country + ": " + amount.toString();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al formatear la cantidad", e);
        }
    }

    private String formatAmountSpain(BigDecimal amount) {
        DecimalFormat decimalFormat = getDecimalFormat(new Locale("es", "ES"));
        return "ES: " + decimalFormat.format(amount.setScale(2, RoundingMode.CEILING)) + " " + CURRENCY_SYMBOL;
    }

    private String formatAmountFrance(BigDecimal amount) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.FRANCE);
        symbols.setCurrencySymbol(CURRENCY_SYMBOL);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');
    
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
    
        return "FR: " + decimalFormat.format(amount) + " " + CURRENCY_SYMBOL;
    }

    private String formatAmountUS(BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat(FORMATO_US, new DecimalFormatSymbols(Locale.US));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return "US: " + "$" + decimalFormat.format(amount);
    }

    private DecimalFormat getDecimalFormat(Locale locale) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(locale);
        symbols.setCurrencySymbol(CURRENCY_SYMBOL);
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
    
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        return decimalFormat;
    }

    public String[] getAvailableCountries() {
        return new String[]{"España", "Francia", "Estados Unidos"};
    }
}