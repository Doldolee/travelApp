package com.seook.travelapp_hanium.travel_destination_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.ActivityMainSearchBinding
import com.seook.travelapp_hanium.retrofit.CityModel
import com.seook.travelapp_hanium.retrofit.RetrofitManager
import com.seook.travelapp_hanium.utils.RESPONSE_STATE
import java.util.*
import kotlin.collections.ArrayList

class MainSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainSearchBinding
    private lateinit var rvAdapter: SearchRVAdapter
    private var items = ArrayList<CityModel>()
    val displayList = ArrayList<CityModel>()

    val TAG : String ="로그"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_search)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_search)


        CityApi()

        rvAdapter = SearchRVAdapter(baseContext, items)
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
                            if(it.name.lowercase(Locale.getDefault()).contains(search)){
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

    private fun CityApi(){
        RetrofitManager.instance.searchCity(completion = {
                responsestate, responseDataArrayList ->
            when(responsestate){
                RESPONSE_STATE.OKAY->{
                    Log.d(TAG, "MainSearchActivity - CityApi() called//api호출완료")
                    for(city in responseDataArrayList){
                        items.add(city)
                    }
                    rvAdapter.notifyDataSetChanged()

                }
                RESPONSE_STATE.FAIL->{
                Log.d(TAG, "SearchFragment - ProductApi() called/ 호출실패")

            }
            }
        })
    }


}