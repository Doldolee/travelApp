package com.seook.travelapp_hanium.api

import retrofit2.http.GET

const val BASE_URL = "http://13.125.233.80:8080/"

interface ApiRequest{

    @GET("nationList")
    suspend fun getNationList(): ArrayList<ApiData>
}