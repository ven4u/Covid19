package com.android.covid19.api.covid19;

import com.android.covid19.model.covid19.GetCountriesResponse;
import com.android.covid19.model.covid19.GetCountryTotalAllStatusResponse;
import com.android.covid19.model.covid19.GetWorldTotalResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by venkatesh on 5/12/2020
 */
public interface Covid19ApiInterface {

    @GET("{path}")
    Call<GetWorldTotalResponse> getWorldTotalMethod(@Path("path") String path);
    @GET("{path}")
    Call<List<GetCountriesResponse>>getCountriesMethod(@Path("path") String path);
    @GET("{path}")
    Call<List<GetCountryTotalAllStatusResponse>> GetCountryTotalAllStatusMethod(@Path("path") String path);

//        @GET("{path}")
//        Call<GetVendingSlotsResponse> getVendingSlotsMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<GetVendingResponse> getVendingMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<ResponseBody> downloadImageMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<GetUITplResponse> getUITplMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<GetSkuResponse> getSkuMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<CreateOrderResponse> createOrderMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<SetVendingSlotsResponse> setVendingSlotsMethod(@Path("path")  String path);
//
//        @GET("{path}")
//        Call<GetOrderStatusResponse> getOrderStatusMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<CreateQCodeResponse> CreateQCodeMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<CheckQCodeResponse> CheckQCodeMethod(@Path("path")String path);
//
//        @GET("{path}")
//        Call<GetCorpBalanceResponse> GetCorpBalanceMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<GetVendingResponse> GetVendingResponse(@Path("path") String path);
//
//        @GET("{path}")
//        Call<DeliverOrderResponse> DeliverOrderMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<WriteUXLogResponse> LogUXWriteMethod(@Path("path") String path);
//
//        @GET("{path}")
//        Call<CheckVersionResponse> CheckVersionMethod(@Path("path") String path);
}


