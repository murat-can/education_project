package com.example.loginandproduclist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET("products")
    Call<List<UrunleriGosterenSayfa>> getirilendatalar();
    @GET
    Call<UrunlerinDetaylariniGosterici> getirilenurundatalar(@Url String url);
    @GET //("products/categories")
    Call<String[]> categorygetdata(@Url String url);

}
