package com.avukelic.currencyconverter.network;

import com.avukelic.currencyconverter.model.ExchangeRate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by avukelic on 13-Aug-18.
 */
public interface ApiService {

    @GET("/api/v1/rates/daily/")
    Call<List<ExchangeRate>> getTodayExchangeRates();

}
