package com.seook.travelapp_hanium.main_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.R

class SearchRVAdapter(val items: ArrayList<SearchModel>):RecyclerView.Adapter<SearchRVAdapter.Viewholder>() {
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

    inner class Viewholder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(item:SearchModel){
            val cityTitle = itemView.findViewById<TextView>(R.id.cityArea)

            cityTitle.text = item.cityTitle





        }
    }
}