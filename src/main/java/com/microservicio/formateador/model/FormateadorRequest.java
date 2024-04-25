package com.microservicio.formateador.model;

import java.math.BigDecimal;

public class FormateadorRequest {
    private BigDecimal amount;
    private String countryCode;

    public FormateadorRequest(BigDecimal amount, String countryCode) {
        this.amount = amount;
        this.countryCode = countryCode;
    }
 
    // Getters y setters
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}