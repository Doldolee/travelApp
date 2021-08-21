package com.seook.travelapp_hanium.main_search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.MainActivity
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.fragments.SearchFragment

class SearchRVAdapter(val context: Context, val items: ArrayList<SearchModel>) :
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
        fun bindItems(item: SearchModel) {
            Log.d(TAG, "bind함수 실행")
            Log.d(TAG, item.toString())
            val cityTitle = itemView.findViewById<TextView>(R.id.cityArea)

            cityTitle.text = item.cityTitle

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