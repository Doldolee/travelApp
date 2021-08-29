package com.seook.travelapp_hanium.travel_destination_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.retrofit.CityModel

class SearchRVAdapter(val context: Context, val items: ArrayList<CityModel>) :
    RecyclerView.Adapter<SearchRVAdapter.Viewholder>() {

    private val TAG = SearchRVAdapter::class.java.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.main_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: SearchRVAdapter.Viewholder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: CityModel) {
            val cityTitle = itemView.findViewById<TextView>(R.id.cityArea)

            cityTitle.text = item.name


//            itemView.setOnClickListener {
//                //이 부분 SearchFragment로 보내고 싶은데 모르겠습니다.
//
//                val intent = Intent(context, MainActivity::class.java)
//                intent.putExtra("city", item.cityTitle)
//                itemView.context.startActivity(intent)
//            }
        }
    }
}