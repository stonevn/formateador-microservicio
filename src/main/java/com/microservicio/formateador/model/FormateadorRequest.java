package com.microservicio.formateador.model;

import java.math.BigDecimal;

public class FormateadorRequest {
    private BigDecimal amount;
    private String country;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "FormateadorRequest [amount=" + amount + ", country=" + country + "]";
    }

    
}