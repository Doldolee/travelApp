package com.seook.travelapp_hanium.fragments

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.MainActivity
import com.seook.travelapp_hanium.ProductDetailActivity
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.retrofit.ProductModel
import com.seook.travelapp_hanium.utils.App

class ProductSearchRVAdapter(val context: Context, val items: ArrayList<ProductModel>): RecyclerView.Adapter<ProductSearchRVAdapter.Viewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSearchRVAdapter.Viewholder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_rv_item, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: ProductSearchRVAdapter.Viewholder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size

    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(item: ProductModel){

            val productName = itemView.findViewById<TextView>(R.id.createdAttext)
            val price = itemView.findViewById<TextView>(R.id.productPrice)
            val clicked = itemView.findViewById<TextView>(R.id.likesCountText)

            productName.text = item.name.toString()
            price.text = item.price.toString()
            clicked.text = item.clicked.toString()




            itemView.setOnClickListener {
                val intent = Intent(context, ProductDetailActivity::class.java)
                intent.putExtra("name", item.name)
                intent.putExtra("picture", item.picture)
                intent.putExtra("price", item.price.toString())
                intent.putExtra("clicked", item.clicked)
                itemView.context.startActivity(intent)
            }




        }

    }
}