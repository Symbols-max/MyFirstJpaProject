package org.example;

import javax.persistence.Column;
import javax.persistence.Id;

import javax.persistence.*;
@Entity
public class Currency {
    @Id()
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private float saleRateNB;
    @Column(nullable = false)
    private float purchaseRateNB;




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getSaleRateNB() {
        return saleRateNB;
    }

    public void setSaleRateNB(float saleRateNB) {
        this.saleRateNB = saleRateNB;
    }

    public float getPurchaseRateNB() {
        return purchaseRateNB;
    }

    public void setPurchaseRateNB(float purchaseRateNB) {
        this.purchaseRateNB = purchaseRateNB;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "date=" + date +", currency="+currency+", saleRateNB="+saleRateNB+", purchaseRateNB="+purchaseRateNB+"}";
    }

}
