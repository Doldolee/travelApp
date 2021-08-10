package com.seook.travelapp_hanium.main_search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.MainActivity
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.api.ApiData
import com.seook.travelapp_hanium.api.ApiRequest
import com.seook.travelapp_hanium.api.BASE_URL
import com.seook.travelapp_hanium.databinding.ActivityMainSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSearchBinding

    private lateinit var rvAdapter: SearchRVAdapter
    private var items = ArrayList<SearchModel>()
//    private var itemsx = mutableListOf<String>()
    private val TAG = MainSearchActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_search)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_search)

        binding.backBtn.setOnClickListener {
            finish()
        }

        makeApiRequest()

        rvAdapter = SearchRVAdapter(baseContext, items)

        val rv :RecyclerView = binding.rv
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)


    }

    private fun makeApiRequest(){
        Log.d(TAG, "startAPI")
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                Log.d(TAG, "tryAPI")
                var response = api.getNationList()
//                Log.d("Main", "Size: ${response.name}")
//                items.add(SearchModel("a",response.name))
                for (data in response){
//                    Log.d("Main", "Size: ${data.name}")
                    items.add(SearchModel("url", data.name))
                    Log.d(TAG, "addApi")
                }
                rvAdapter.notifyDataSetChanged()

            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }


    }
}