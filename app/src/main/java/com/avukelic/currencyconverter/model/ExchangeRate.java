package com.avukelic.currencyconverter.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class ExchangeRate extends RealmObject {

    @Expose
    @SerializedName("currency_code")
    private String currencyCode;
    @Expose
    @SerializedName("unit_value")
    private int unit;
    @Expose
    @SerializedName("buying_rate")
    private double buyingRate;
    @Expose
    @SerializedName("selling_rate")
    private double sellingRate;

    public ExchangeRate() {
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(double buyingRate) {
        this.buyingRate = buyingRate;
    }

    public double getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(double sellingRate) {
        this.sellingRate = sellingRate;
    }
}
