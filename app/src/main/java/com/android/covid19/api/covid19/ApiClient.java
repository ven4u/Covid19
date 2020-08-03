package com.android.covid19.api.covid19;

/**
 * Created by venkatesh on 5/12/2020.
 */


import com.android.covid19.common.Config;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = Config._domain_covid19;
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;



    public static Retrofit getClient() {
        if (retrofit==null) {
//            okHttpClient = new OkHttpClient().newBuilder()
//                    .connectTimeout(70, TimeUnit.SECONDS)
//                    .readTimeout(70, TimeUnit.SECONDS)
//                    .writeTimeout(70, TimeUnit.SECONDS)
//                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
