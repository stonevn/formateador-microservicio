package com.microservicio.formateador.model;

import java.util.Objects;

public class CountryAmountFormatResult {
    private String countryCode;
    private String formattedAmount;
    private String currencySymbol;

    public CountryAmountFormatResult(String countryCode, String formattedAmount, String currencySymbol) {
        this.countryCode = countryCode;
        this.formattedAmount = formattedAmount;
        this.currencySymbol = currencySymbol;
    }

    public CountryAmountFormatResult(){
    }
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getFormattedAmount() {
        return formattedAmount;
    }

    public void setFormattedAmount(String formattedAmount) {
        this.formattedAmount = formattedAmount;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    } 
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryAmountFormatResult that = (CountryAmountFormatResult) o;
        return Objects.equals(countryCode, that.countryCode) &&
                Objects.equals(formattedAmount, that.formattedAmount) &&
                Objects.equals(currencySymbol, that.currencySymbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, formattedAmount, currencySymbol);
    }
    
    @Override
    public String toString() {
        return "CountryAmountFormatResult [countryCode=" + countryCode + ", formattedAmount=" + formattedAmount
                + ", currencySymbol=" + currencySymbol + "]";
    }
    
}