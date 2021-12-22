package com.example.nobdeneme.Service;

import com.example.nobdeneme.Models.DataEczaneler;
import com.example.nobdeneme.Models.DataIlceler;
import com.example.nobdeneme.Models.DataSehirler;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface EczanelerAPI {
    //------------------------------------------------------------
    @Headers({"Content-Type: application/json"
            ,"Authorization: Bearer API-KEY"})
    @GET
    Call<DataEczaneler> bulunulanIl(@Url String url);
    //------------------------------------------------------------
    @Headers({"Content-Type: application/json"
            ,"Authorization: Bearer API-KEY"})
    @GET("city/")
    Call<DataSehirler> iller();
    //------------------------------------------------------------
    @Headers({"Content-Type: application/json"
            ,"Authorization: Bearer API-KEY"})
    @GET
    Call<DataIlceler> ilceler(@Url String url);
//city?city=ankara
    //------------------------------------------------------------

















}
