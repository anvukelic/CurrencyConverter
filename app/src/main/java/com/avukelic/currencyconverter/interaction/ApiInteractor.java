package com.avukelic.currencyconverter.interaction;

import com.avukelic.currencyconverter.model.ExchangeRate;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by avukelic on 13-Aug-18.
 */
public interface ApiInteractor {
    void getExchangeRates(Callback<List<ExchangeRate>> callback);
}
