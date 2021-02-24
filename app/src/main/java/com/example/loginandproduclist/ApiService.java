package com.example.loginandproduclist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {
    @GET("products")
    Call<List<Product>> GetProducts();

    @GET
    Call<ProductDetail> GetProductDetail(@Url String url); //liste şeklinde dönmüyor tek tek gidecek hangi itemViewe tıklarsak

    @GET
    Call<String[]> GetCategories(@Url String url);//4 tane döndüğü için dizi olarak tanımlanır
}
