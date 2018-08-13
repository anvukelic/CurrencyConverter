package com.avukelic.currencyconverter.interaction;

import com.avukelic.currencyconverter.App;
import com.avukelic.currencyconverter.model.ExchangeRate;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class ApiInteractorImpl implements ApiInteractor {



    @Override
    public void getExchangeRates(Callback<List<ExchangeRate>> callback) {
        App.getApiService().getTodayExchangeRates().enqueue(callback);

    }
}
