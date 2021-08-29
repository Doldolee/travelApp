package com.seook.travelapp_hanium.retrofit

import android.util.Log
import com.google.gson.JsonElement
import com.seook.travelapp_hanium.utils.API
import com.seook.travelapp_hanium.utils.RESPONSE_STATE
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {
    val TAG : String ="로그"

    companion object{
        val instance = RetrofitManager()
    }

    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun searchProduct(completion: (RESPONSE_STATE, ArrayList<ProductModel>)->Unit){
        val call = iRetrofit?.searchProduct().let{
            it
        }?: return

        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ ${response.body()}")

                when(response.code()){
                    200->{
                        response.body()?.let{

                            var parsedProductDataArray = ArrayList<ProductModel>()
                            val body = it.asJsonArray
                            body.forEach{
                                val resultItemObject = it.asJsonObject
                                Log.d(TAG, "RetrofitManager - onResponse() called/ tttoal:  ${resultItemObject}")
                                val id = resultItemObject.get("id").asInt
                                val name = resultItemObject.get("name").asString
                                val picture = resultItemObject.get("picture").asString
                                val price = resultItemObject.get("price").asInt
                                val clicked = resultItemObject.get("clicked").asInt
                                val ProductItem = ProductModel(
                                    id = id,
                                    name = name,
                                    picture = picture,
                                    price = price,
                                    clicked = clicked
                                )
                                parsedProductDataArray.add(ProductItem)
                            }
                            //completion으로 응답값만 받음.
                            completion(RESPONSE_STATE.OKAY, parsedProductDataArray )
                        }

                    }
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null!!)
            }

        })
    }

    fun searchCity(completion: (RESPONSE_STATE, ArrayList<CityModel>)->Unit){
        val call = iRetrofit?.searchCity().let{
            it
        }?: return

        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - onResponse() called/ ${response.body()}")

                when(response.code()){
                    200->{
                        response.body()?.let{

                            var parsedProductDataArray = ArrayList<CityModel>()
                            val body = it.asJsonArray
                            body.forEach{
                                val resultItemObject = it.asJsonObject
//                                Log.d(TAG, "RetrofitManager - onResponse() called/ tttoal:  ${resultItemObject}")
                                val id = resultItemObject.get("id").asInt
                                val name = resultItemObject.get("name").asString
                                Log.d(TAG, "RetrofitManager - onResponse() called->->-> ${name}")
                                val picture = resultItemObject.get("picture").asString
                                val detail = resultItemObject.get("detail").asString
                                val clicked = resultItemObject.get("clicked").asInt
                                val ProductItem = CityModel(
                                    id = id,
                                    name = name,
                                    picture = picture,
                                    detail = detail,
                                    clicked = clicked
                                )
                                parsedProductDataArray.add(ProductItem)
                            }
                            //completion으로 응답값만 받음.
                            completion(RESPONSE_STATE.OKAY, parsedProductDataArray )
                        }

                    }
                }
            }
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - onFailure() called / t: $t")
                completion(RESPONSE_STATE.FAIL, null!!)
            }

        })
    }

}