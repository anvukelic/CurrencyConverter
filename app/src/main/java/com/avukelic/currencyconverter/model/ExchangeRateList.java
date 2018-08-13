package com.avukelic.currencyconverter.model;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class ExchangeRateList extends RealmObject implements RealmModel {

    private RealmList<ExchangeRate> rates = new RealmList<>();

    public ExchangeRateList() {
    }

    public RealmList<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates.addAll(rates);
    }
}
