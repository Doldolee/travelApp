package com.seook.travelapp_hanium.api2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET("/nations")
    fun nationSearch(
        @Query("nationName") nationName: String,
    ): Call<NationOkResponse>
}