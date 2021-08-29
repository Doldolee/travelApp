package com.seook.travelapp_hanium.retrofit

import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.seook.travelapp_hanium.utils.API
import com.seook.travelapp_hanium.utils.isJsonArray
import com.seook.travelapp_hanium.utils.isJsonObject
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofitClient : Retrofit? = null
    val TAG : String ="로그"

    fun getClient(baseUrl:String):Retrofit?{

        val client = OkHttpClient.Builder()

        val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
            override fun log(message: String) {
                Log.d(TAG, "RetrofitClient - log() called / message: $message")

                when {
                    message.isJsonObject() -> Log.d(TAG, "JSONObject(message).toString(4)")
                    message.isJsonArray() -> Log.d(TAG, "JSONObject(message).toString(4)")
                    else -> {
                        try{
                            Log.d(TAG, JSONObject(message).toString(4))

                        }catch (e: Exception){
                            Log.d(TAG, message)

                        }
                    }

                }

            }
        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        //위에서 설정한 인터셉터를 okHttp클라이언트에 추가한다.
        client.addInterceptor(loggingInterceptor)

//        // 기본 파라미터 추가(액세스 토큰)
//        val baseParameterInterceptor: Interceptor = (object : Interceptor {
//            override fun intercept(chain: Interceptor.Chain): Response {
//                Log.d(TAG, "RetrofitClient - intercept() called")
//                //오리지날 리퀘스트
//                val originalRequest = chain.request()
//                //쿼리 파라미터 추가하기
//                val addedUrl = originalRequest.url.newBuilder().addQueryParameter("client_id", API.CLIENT_ID).build()
//
//                val finalRequest = originalRequest.newBuilder()
//                    .url(addedUrl)
//                    .method(originalRequest.method, originalRequest.body)
//                    .build()
//
//
////                return chain.proceed(finalRequest)
//                val response = chain.proceed(finalRequest)
//
//                if(response.code != 200){
//
//                    android.os.Handler(Looper.getMainLooper()).post{
//
//                        Toast.makeText(App.instance, "${response.code}에러입니다.", Toast.LENGTH_SHORT).show()
//
//                    }
//
//                }
//
//                return response
//
//            }
//
//        })
        //위에서 설정한 기본파라미터 인터셉터를 okHttp클라이언트에 추가한다.
//        client.addInterceptor(baseParameterInterceptor)

        //커넥션 타임아웃
        client.connectTimeout(10, TimeUnit.SECONDS)
        client.readTimeout(10, TimeUnit.SECONDS)
        client.writeTimeout(10, TimeUnit.SECONDS)
        client.retryOnConnectionFailure(true)







        if(retrofitClient == null){
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
        }
        return retrofitClient
    }
}