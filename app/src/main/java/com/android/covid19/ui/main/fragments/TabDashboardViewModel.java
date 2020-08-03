package com.android.covid19.ui.main.fragments;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.covid19.api.corona.ApiClientCorona;
import com.android.covid19.api.corona.CoronaApiInterface;
import com.android.covid19.common.Config;
import com.android.covid19.model.corona.Datum;
import com.android.covid19.model.corona.GetCoronaCountriesRequest;
import com.android.covid19.model.corona.GetCoronaCountriesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabDashboardViewModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();
    //private final CoronaCountriesRepository repository;
    GetCoronaCountriesRequest getCoronaCountriesRequest = new GetCoronaCountriesRequest();

    private MutableLiveData<GetCoronaCountriesResponse> getCoronaCountriesResponse;


    public MutableLiveData<GetCoronaCountriesResponse> GetCoronaCountriesRepository(GetCoronaCountriesRequest getCoronaCountriesRequest) {
            if(getCoronaCountriesResponse == null){
                getCoronaCountriesResponse = new MutableLiveData<GetCoronaCountriesResponse>();
            }
            GetCoronaCountries();
        Log.d(TAG, "TabDashboardViewModel :-"  + getCoronaCountriesResponse.toString());
        return getCoronaCountriesResponse;
    }
    public void GetCoronaCountries() {
        CoronaApiInterface apiService = ApiClientCorona.getClient().create(CoronaApiInterface.class);
        String path = "/classes/Coronaviruscases_Covid19Case";

        Log.d(TAG, "GetCoronaCountries   path :- " + Config._domain_corona + path.trim());

        Call<GetCoronaCountriesResponse> call = apiService.getCoronaCountriesMethod(path);

        call.enqueue(new Callback<GetCoronaCountriesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetCoronaCountriesResponse> call, @NonNull Response<GetCoronaCountriesResponse> response) {
                Log.d(TAG, "response.isSuccessful :-" + response.isSuccessful());
                Log.d(TAG, "response.headers() :-" + response.headers());
                Log.d(TAG, "response.errorBody() :-" + response.errorBody());
                Log.d(TAG, "response.message() :-" + response.message());
                Log.d(TAG, "response.raw :-" + response.raw());

                if(response.isSuccessful()){
                    Log.d(TAG, "response.isSuccessful :-" + response.isSuccessful());
                    List<Datum> datas = response.body().getData();
                }
                getCoronaCountriesResponse.setValue(response.body());

            }
            @Override
            public void onFailure(@NonNull Call<GetCoronaCountriesResponse> call, @NonNull Throwable t) {
                getCoronaCountriesResponse.postValue(null);
                t.printStackTrace();
            }
        });
       // return getCoronaCountriesResponse;
    }
//
//    public MutableLiveData<GetCoronaCountriesResponse> GetCoronaCountries(GetCoronaCountriesRequest getCoronaCountriesRequest){
//
//        try {
//            Log.d(TAG, "GetCoronaCountriesSynchronousApi Call" );
//            getCoronaCountriesResponse = new MutableLiveData<GetCoronaCountriesResponse>();
//            gccr = csapi.GetCoronaCountriesSynchronous(getCoronaCountriesRequest);
//            getCoronaCountriesResponse.setValue(gccr);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d(TAG, String.valueOf(e)+"IOException");
//            getCoronaCountriesResponse.setValue(gccr);
//            //flag = true;
//        }
//        return getCoronaCountriesResponse;
//       }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared");
    }
}
