package com.seook.travelapp_hanium.main_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.api.ApiData
import com.seook.travelapp_hanium.api.ApiRequest
import com.seook.travelapp_hanium.api.BASE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainSearchActivity : AppCompatActivity() {

    private lateinit var rvAdapter: SearchRVAdapter
    private var items = ArrayList<SearchModel>()
    private val TAG = MainSearchActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search)



        makeApiRequest()

        rvAdapter = SearchRVAdapter(items)

        val rv :RecyclerView = findViewById(R.id.rv)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)


    }

    private fun makeApiRequest(){
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                var response = api.getNationList()
//                Log.d("Main", "Size: ${response.name}")
//                items.add(SearchModel("a",response.name))
                for (data in response){
//                    Log.d("Main", "Size: ${data.name}")
                    items.add(SearchModel("url", data.name))
                }
                rvAdapter.notifyDataSetChanged()

            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }


    }
}