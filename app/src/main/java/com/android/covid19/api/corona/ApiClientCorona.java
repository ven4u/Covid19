package com.android.covid19.api.corona;

/**
 * Created by venkatesh on 5/12/2020.
 */


import com.android.covid19.common.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientCorona {

    public static final String BASE_URL = Config._domain_corona;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;



    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
