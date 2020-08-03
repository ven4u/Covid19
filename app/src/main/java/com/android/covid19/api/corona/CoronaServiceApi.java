package com.android.covid19.api.corona;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.covid19.api.covid19.ApiClient;
import com.android.covid19.api.covid19.Covid19ApiInterface;
import com.android.covid19.common.Config;
import com.android.covid19.model.corona.GetCoronaCountriesRequest;
import com.android.covid19.model.corona.GetCoronaCountriesResponse;
import com.android.covid19.model.covid19.GetCountriesRequest;
import com.android.covid19.model.covid19.GetCountriesResponse;
import com.android.covid19.model.covid19.GetCountryTotalAllStatusRequest;
import com.android.covid19.model.covid19.GetCountryTotalAllStatusResponse;
import com.android.covid19.model.covid19.GetWorldTotalRequest;
import com.android.covid19.model.covid19.GetWorldTotalResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CoronaServiceApi {
    final String TAG = this.getClass().getName();
     Config config  = new Config();
    private final MutableLiveData<GetCoronaCountriesResponse> getCoronaCountriesResponse = new MutableLiveData<GetCoronaCountriesResponse>();

    private static CoronaServiceApi _instance;
    public static CoronaServiceApi Instance()
    {
        if (_instance == null)
            _instance = new CoronaServiceApi();
        return _instance;
    }

    public CoronaServiceApi()
    {
    }


//   public void GetWorldTotal() {
//
//        GetWorldTotalResponse type = new GetWorldTotalResponse();
//        //GetWorldTotalRequest gwtrtemp = new GetVendingRequest();
//
//        Covid19ApiInterface apiService = ApiClient.getClient().create(Covid19ApiInterface.class);
//
//        //Gson g = new Gson();
//        //String request = g.toJson(gvr);
//        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
//       // String path = BuildApiUrl(method, request);
//
//        String path = Config._domain_corona + "/world/total";
//
//        Log.d(TAG, "GetWorldTotalRequest   path :- " + path);
//
//        Call<GetWorldTotalResponse> call = apiService.getWorldTotalMethod(path);
//
//        call.enqueue(new Callback<GetWorldTotalResponse>() {
//
//
//            @Override
//            public void onResponse(Call<GetWorldTotalResponse> call, retrofit2.Response<GetWorldTotalResponse> response) {
//
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "response.isSuccessful() :- " + response.isSuccessful());
//                    GetWorldTotalResponse repos = response.body();
//
//                    Log.d(TAG, " repos.getTotalConfirmed() :- " +  repos.getTotalConfirmed());
//                    Log.d(TAG, " repos.getTotalDeaths() :- " + repos.getTotalDeaths());
//                    Log.d(TAG, "repos.getTotalRecovered() :- " + repos.getTotalRecovered());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetWorldTotalResponse> call, Throwable t) {
//
//            }
//
//
//        });
//
//    }
    public MutableLiveData<GetCoronaCountriesResponse> GetCoronaCountriesSynchronous(GetCoronaCountriesRequest getCoronaCountriesRequest) throws IOException {
        GetCoronaCountriesRequest type = new GetCoronaCountriesRequest();
        //GetWorldTotalRequest getWorldTotalRequesttemp = new GetWorldTotalRequest();

        CoronaApiInterface apiService = ApiClientCorona.getClient().create(CoronaApiInterface.class);

        //Gson g = new Gson();
        //String request = g.toJson(createOrderRequest);
        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
        String path = Config._domain_corona+"/countries";

        Log.d(TAG, "GetCoronaCountriesRequest   path :- " + path);

        Call<GetCoronaCountriesResponse> call = apiService.getCoronaCountriesMethod(path);
        GetCoronaCountriesResponse response  = call.execute().body();
        getCoronaCountriesResponse.setValue(response);
        return getCoronaCountriesResponse;

    }


}
