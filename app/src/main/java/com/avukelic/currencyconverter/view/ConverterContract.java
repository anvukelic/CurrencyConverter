package com.avukelic.currencyconverter.view;

import java.util.List;

/**
 * Created by avukelic on 13-Aug-18.
 */
public interface ConverterContract {

    interface View {
        void displayConvertResult(double result);
        void showCurrencyCodes(List<String> codes);
        void showNetworkErrorMessage();
    }

    interface Presenter {
        void setView(ConverterContract.View view);
        void getExchangeRates();
        void getCurrencyCodes();
        void convert(double value, String firstCurrencyCode, String secondCurrencyCode);
        void convertFromHrk(double value, String code);
        void convertToHrk(double value, String code);
    }
}
