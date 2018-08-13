package com.avukelic.currencyconverter;

import android.app.Application;

import com.avukelic.currencyconverter.network.ApiService;
import com.avukelic.currencyconverter.network.RetrofitUtil;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Retrofit;

/**
 * Created by avukelic on 13-Aug-18.
 */
public class App extends Application {
    Retrofit retrofit;
    static ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("currencyConverter.ralm")
                .schemaVersion(2)
                .build();
        Realm.setDefaultConfiguration(realmConfig);

        retrofit = RetrofitUtil.createRetrofit();
        apiService = retrofit.create(ApiService.class);


    }

    public static ApiService getApiService() {
        return apiService;
    }
}
