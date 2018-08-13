package com.avukelic.currencyconverter.interaction;

import com.avukelic.currencyconverter.model.ExchangeRate;
import com.avukelic.currencyconverter.model.ExchangeRateList;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class DbInteractorImpl implements DbInteractor {

    private Realm realm;

    public DbInteractorImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public List<String> getCurrencyCodes() {
        List<String> codes = new ArrayList<>();
        System.out.println(realm.where(ExchangeRateList.class).findFirst().getRates());
        for (ExchangeRate rate : realm.where(ExchangeRateList.class).findFirst().getRates()) {
            codes.add(rate.getCurrencyCode());
        }
        return codes;
    }

    @Override
    public double convertToHrk(String code, double value) {
        RealmList<ExchangeRate> rates = realm.where(ExchangeRateList.class).findFirst().getRates();
        double rate = rates.where().equalTo("currencyCode", code).findFirst().getBuyingRate();
        int unit = rates.where().equalTo("currencyCode", code).findFirst().getUnit();
        System.out.println(value * (rate/unit));
        return value * (rate/unit);
    }

    @Override
    public double convertFromHrk(String code, double value) {
        RealmList<ExchangeRate> rates = realm.where(ExchangeRateList.class).findFirst().getRates();
        double rate = rates.where().equalTo("currencyCode", code).findFirst().getSellingRate();
        int unit = rates.where().equalTo("currencyCode",code).findFirst().getUnit();
        System.out.println(value / (rate/unit));
        return value / (rate/unit);
    }


}
