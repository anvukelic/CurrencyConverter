package com.avukelic.currencyconverter.presenter;

import com.avukelic.currencyconverter.interaction.ApiInteractor;
import com.avukelic.currencyconverter.interaction.ApiInteractorImpl;
import com.avukelic.currencyconverter.interaction.DbInteractor;
import com.avukelic.currencyconverter.interaction.DbInteractorImpl;
import com.avukelic.currencyconverter.model.ExchangeRate;
import com.avukelic.currencyconverter.model.ExchangeRateList;
import com.avukelic.currencyconverter.view.ConverterContract;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class ConverterPresenter implements ConverterContract.Presenter {

    public ConverterPresenter() {
        realm = Realm.getDefaultInstance();
        apiInteractor = new ApiInteractorImpl();
        dbInteractor = new DbInteractorImpl();
    }

    private final ApiInteractor apiInteractor;
    private final DbInteractor dbInteractor;
    private ConverterContract.View view;
    private Realm realm;

    @Override
    public void setView(ConverterContract.View view) {
        this.view = view;
    }

    @Override
    public void getExchangeRates() {
        apiInteractor.getExchangeRates(getExchangeRatesCallback());
    }

    @Override
    public void getCurrencyCodes() {
        view.showCurrencyCodes(dbInteractor.getCurrencyCodes());
    }

    @Override
    public void convert(double value, String firstCurrencyCode, String secondCurrencyCode) {
        view.displayConvertResult(dbInteractor.convertFromHrk(secondCurrencyCode, dbInteractor.convertToHrk(firstCurrencyCode, value)));
    }

    @Override
    public void convertFromHrk(double value, String code) {
        view.displayConvertResult(dbInteractor.convertFromHrk(code, value));
    }

    @Override
    public void convertToHrk(double value, String code) {
        view.displayConvertResult(dbInteractor.convertToHrk(code, value));
    }


    private Callback<List<ExchangeRate>> getExchangeRatesCallback() {
        return new Callback<List<ExchangeRate>>() {
            @Override
            public void onResponse(Call<List<ExchangeRate>> call, final Response<List<ExchangeRate>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        realm.beginTransaction();
                        ExchangeRateList rates = realm.createObject(ExchangeRateList.class);
                        rates.setRates(response.body());
                        realm.commitTransaction();
                    }
                }

            @Override
            public void onFailure(Call<List<ExchangeRate>> call, Throwable t) {
                view.showNetworkErrorMessage();
            }
        };
    }
}
