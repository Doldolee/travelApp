package com.seook.travelapp_hanium.main_search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.MainActivity
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.api.ApiData
import com.seook.travelapp_hanium.api.ApiRequest
import com.seook.travelapp_hanium.api.BASE_URL
import com.seook.travelapp_hanium.api2.NationErrorResponse
import com.seook.travelapp_hanium.api2.NationOkResponse
import com.seook.travelapp_hanium.api2.UserApiMethod
import com.seook.travelapp_hanium.databinding.ActivityMainSearchBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MainSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSearchBinding
    private lateinit var rvAdapter: SearchRVAdapter
    private var items = ArrayList<SearchModel>()
    val displayList = ArrayList<SearchModel>()




    private val TAG = MainSearchActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_search)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_search)



//        binding.backBtn.setOnClickListener {
//            finish()
//        }

//        items.add(SearchModel("url1","data"))
//        items.add(SearchModel("url2","nation"))
//        items.add(SearchModel("url3","country"))

        makeApiRequest()

        rvAdapter = SearchRVAdapter(baseContext, displayList)
        val rv :RecyclerView = binding.rv
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem != null){
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.lowercase(Locale.getDefault())
                        items.forEach{
                            if(it.cityTitle.lowercase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        rvAdapter.notifyDataSetChanged()
                    }
                    else{
                        displayList.clear()
                        displayList.addAll(items)
                        rvAdapter.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    private fun makeApiRequest(){
//        Log.d(TAG, "startAPI")
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
//                Log.d(TAG, "tryAPI")
                var response = api.getNationList()
//                Log.d("Main", "Size: ${response.name}")
                for (data in response){
//                    Log.d("Main", "Size: ${data.name}")
                    items.add(SearchModel("url", data.name))
//                    Log.d(TAG, data.toString())
                }
//                Log.d(TAG, items.toString())
                displayList.addAll(items)
                rvAdapter.notifyDataSetChanged()

            } catch (e: Exception) {
                Log.e("Main", "Error: ${e.message}")
            }
        }
    }

//    private fun nationSearch(
//        nationName: String
//    ) {
//        UserApiMethod.nationSearch(
//            nationName
//        ) { response ->
//
//            when (response) {
//                is NationOkResponse -> {
//                    Log.d("tag", response.name)
//                    items.add(SearchModel("url1",response.name))
//                    rvAdapter.notifyDataSetChanged()
//                    Log.d(TAG, items.toString())
//                }
//                is NationErrorResponse -> {
//
////                    Log.d("tag", "" + response.code)
//                    Log.d("tag", response.message)
//                    Log.d("tag", response.httpStatus)
//                    Toast.makeText(this, "해당 국가는 없습니다.", Toast.LENGTH_LONG).show()
//                }
//                else -> {
//                    Toast.makeText(
//                        applicationContext,
//                        "에러가 발생하여 가입 여부 확인에 실패",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//        }
//    }
}