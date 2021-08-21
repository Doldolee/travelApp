package com.seook.travelapp_hanium.api2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object{

        //통신할 서버 url
        private const val baseUrl = "http://13.125.233.80:8080"

        //Retrofit 객체 초기화
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val test: Service = retrofit.create(Service::class.java)
    }
}