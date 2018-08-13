package com.avukelic.currencyconverter.interaction;

import java.util.List;

import io.realm.Realm;

/**
 * Created by avukelic on 13-Aug-18.
 */
public interface DbInteractor {

    List<String> getCurrencyCodes();

    double convertToHrk(String code, double value);

    double convertFromHrk(String code, double value);
}
