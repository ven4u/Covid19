package com.android.covid19.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.android.covid19.api.corona.ApiClientCorona;
import com.android.covid19.api.corona.CoronaApiInterface;
import com.android.covid19.common.Config;
import com.android.covid19.model.corona.GetCoronaCountriesRequest;
import com.android.covid19.model.corona.GetCoronaCountriesResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CoronaCountriesRepository {

    private final MutableLiveData<GetCoronaCountriesResponse> getCoronaCountriesResponse = new MutableLiveData<GetCoronaCountriesResponse>();

    private static CoronaCountriesRepository newsRepository;

    public static CoronaCountriesRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new CoronaCountriesRepository();
        }
        return newsRepository;
    }

    public CoronaCountriesRepository(){

    }

//    public MutableLiveData<GetCoronaCountriesResponse> GetCoronaCountries(GetCoronaCountriesRequest getCoronaCountriesRequest) {
//        CoronaApiInterface apiService = ApiClientCorona.getClient().create(CoronaApiInterface.class);
//        String path = Config._domain_corona + "/countries";
//
//        Log.d(TAG, "GetCoronaCountries   path :- " + path);
//
//        Call<GetCoronaCountriesResponse> call = apiService.getCoronaCountriesMethod();
//
//        call.enqueue(new Callback<GetCoronaCountriesResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<GetCoronaCountriesResponse> call, @NonNull Response<GetCoronaCountriesResponse> response) {
//                getCoronaCountriesResponse.setValue(response.body());
//                Log.d(TAG, "GetCoronaCountries onResponse :-" + getCoronaCountriesResponse.toString());
//            }
//            @Override
//            public void onFailure(@NonNull Call<GetCoronaCountriesResponse> call, @NonNull Throwable t) {
//                getCoronaCountriesResponse.postValue(null);
//                Log.d(TAG, "GetCoronaCountries onFailure");
//            }
//        });
//        return getCoronaCountriesResponse;
//    }
//
//    public MutableLiveData<GetCoronaCountriesResponse> GetCoronaCountriesSynchronous(GetCoronaCountriesRequest getCoronaCountriesRequest) throws IOException {
//        GetCoronaCountriesRequest type = new GetCoronaCountriesRequest();
//        //GetWorldTotalRequest getWorldTotalRequesttemp = new GetWorldTotalRequest();
//
//        CoronaApiInterface apiService = ApiClientCorona.getClient().create(CoronaApiInterface.class);
//
//        //Gson g = new Gson();
//        //String request = g.toJson(createOrderRequest);
//        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
//        String path = Config._domain_corona+"/countries";
//
//        Log.d(TAG, "GetCoronaCountriesRequest   path :- " + path);
//
//        Call<GetCoronaCountriesResponse> call = apiService.getCoronaCountriesMethod();
//        GetCoronaCountriesResponse response  = call.execute().body();
//        getCoronaCountriesResponse.setValue(response);
//        return getCoronaCountriesResponse;
//    }
}
