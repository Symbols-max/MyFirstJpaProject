package org.example;

import java.util.ArrayList;

public class Currencies {
String date;
String bank;
int baseCurrency;
String baseCurrencyLit;
ArrayList<ExchangeRate> exchangeRate=new ArrayList<>();
    private ExchangeRate exchangeRateUSD=new ExchangeRate();
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }

    public ArrayList<ExchangeRate> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ArrayList<ExchangeRate> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    public ExchangeRate getExchangeRateUSD() {
        for ( ExchangeRate er : exchangeRate){
            if (er.getCurrency()!= null) {
                if (er.getCurrency().equals("USD")){
                    exchangeRateUSD = er;
                    break;
                }
            }
        }
        return exchangeRateUSD;
    }


    @Override
    public String toString() {
        return "Currencies{" +
                "date=" + date +
                ",bank="+ bank+ ", baseCurrency="+baseCurrency+
        ", baseCurrencyLit="+ baseCurrencyLit+"}";
    }
}
