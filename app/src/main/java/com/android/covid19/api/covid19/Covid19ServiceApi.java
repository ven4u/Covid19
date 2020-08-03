package com.android.covid19.api.covid19;

import android.util.Log;

import com.android.covid19.common.Config;
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

public class Covid19ServiceApi {
    final String TAG = this.getClass().getName();
     Config config  = new Config();

    private static Covid19ServiceApi _instance;
    public static Covid19ServiceApi Instance()
    {
        if (_instance == null)
            _instance = new Covid19ServiceApi();
        return _instance;
    }

    public Covid19ServiceApi()
    {
    }


   public void GetWorldTotal() {

        GetWorldTotalResponse type = new GetWorldTotalResponse();
        //GetWorldTotalRequest gwtrtemp = new GetVendingRequest();

        Covid19ApiInterface apiService = ApiClient.getClient().create(Covid19ApiInterface.class);

        //Gson g = new Gson();
        //String request = g.toJson(gvr);
        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
       // String path = BuildApiUrl(method, request);

        String path = Config._domain_covid19 + "/world/total";

        Log.d(TAG, "GetWorldTotalRequest   path :- " + path);

        Call<GetWorldTotalResponse> call = apiService.getWorldTotalMethod(path);

        call.enqueue(new Callback<GetWorldTotalResponse>() {


            @Override
            public void onResponse(Call<GetWorldTotalResponse> call, retrofit2.Response<GetWorldTotalResponse> response) {

                if (response.isSuccessful()) {
                    Log.d(TAG, "response.isSuccessful() :- " + response.isSuccessful());
                    GetWorldTotalResponse repos = response.body();

                    Log.d(TAG, " repos.getTotalConfirmed() :- " +  repos.getTotalConfirmed());
                    Log.d(TAG, " repos.getTotalDeaths() :- " + repos.getTotalDeaths());
                    Log.d(TAG, "repos.getTotalRecovered() :- " + repos.getTotalRecovered());
                }
            }

            @Override
            public void onFailure(Call<GetWorldTotalResponse> call, Throwable t) {

            }


        });

    }

    public GetWorldTotalResponse GetWorldTotalSynchronous(GetWorldTotalRequest getWorldTotalRequest ) throws IOException {
          GetWorldTotalResponse type = new GetWorldTotalResponse();
        //GetWorldTotalRequest getWorldTotalRequesttemp = new GetWorldTotalRequest();

        Covid19ApiInterface apiService = ApiClient.getClient().create(Covid19ApiInterface.class);

        //Gson g = new Gson();
        //String request = g.toJson(createOrderRequest);
        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
        String path = Config._domain_covid19 + "/world/total";

        Log.d(TAG, "GetWorldTotalRequest   path :- " + path);

        Call<GetWorldTotalResponse> call = apiService.getWorldTotalMethod(path);
        GetWorldTotalResponse response  = call.execute().body();
        Log.d(TAG, response.toString());
        return response;
    }

    public List<GetCountriesResponse> GetCountriesSynchronous(GetCountriesRequest getWorldTotalRequest) throws IOException {
        GetCountriesResponse type = new GetCountriesResponse();
        //GetWorldTotalRequest getWorldTotalRequesttemp = new GetWorldTotalRequest();

        Covid19ApiInterface apiService = ApiClient.getClient().create(Covid19ApiInterface.class);

        //Gson g = new Gson();
        //String request = g.toJson(createOrderRequest);
        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
        String path = Config._domain_covid19 +"/countries";

        Log.d(TAG, "GetCountriesRequest   path :- " + path);

        Call<List<GetCountriesResponse>> call = apiService.getCountriesMethod(path);
        List<GetCountriesResponse> response  = call.execute().body();

        return response;
    }

    public List<GetCountryTotalAllStatusResponse> GetCountryTotalAllStatusSynchronous(GetCountryTotalAllStatusRequest getCountryTotalAllStatusRequest, String slug) throws IOException{
        GetCountryTotalAllStatusResponse type = new GetCountryTotalAllStatusResponse();
        //GetWorldTotalRequest getWorldTotalRequesttemp = new GetWorldTotalRequest();

        Covid19ApiInterface apiService = ApiClient.getClient().create(Covid19ApiInterface.class);

        //Gson g = new Gson();
        //String request = g.toJson(createOrderRequest);
        //Config._access_token = "7B52A7B6DB8614BE58E8E430F096731B";
        String path = "/total/country/"+slug;

        Log.d(TAG, "GetCountriesRequest   path :- " + path);

        Call<List<GetCountryTotalAllStatusResponse>> call = apiService.GetCountryTotalAllStatusMethod(path);
        List<GetCountryTotalAllStatusResponse> response  = call.execute().body();

        return response;

    }
}
