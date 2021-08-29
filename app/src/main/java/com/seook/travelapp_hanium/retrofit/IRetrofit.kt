package com.seook.travelapp_hanium.retrofit

import com.google.gson.JsonElement
import com.seook.travelapp_hanium.utils.API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

    @GET(API.SEARCH_PRODUCT)
    fun searchProduct() : Call<JsonElement>

    @GET(API.SEARCH_CITY)
    fun searchCity() :  Call<JsonElement>

    @GET(API.SEARCH_PRODUCT_DETAIL)
    fun searchProductDetail(@Query("productName") searchTerm : String) :  Call<JsonElement>



}