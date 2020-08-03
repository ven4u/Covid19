package com.android.covid19.api.corona;

import com.android.covid19.model.corona.GetCoronaCountriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface CoronaApiInterface {

    @Headers({
        "X-Parse-Application-Id: vSWieDql2CyP3s2crLh3f96bZ9q8SVj0EK7S5ysw",
        "X-Parse-REST-API-Key: q6laVyVwM4oWMJmvVClT2DZB3ZKzCEpMx0ZJbLfA"
    })
    @GET("{path}")
    Call<GetCoronaCountriesResponse> getCoronaCountriesMethod(@Path("path") String path);
}
