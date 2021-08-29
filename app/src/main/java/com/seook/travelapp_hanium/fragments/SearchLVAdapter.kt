package com.seook.travelapp_hanium.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.retrofit.ProductModel

class SearchLVAdapter(val productList : ArrayList<ProductModel>):BaseAdapter() {
    override fun getCount(): Int {
        return productList.size
    }

    override fun getItem(position: Int): Any {
        return productList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var converView = convertView
        if(converView == null){
        converView = LayoutInflater.from(parent?.context).inflate(R.layout.product_lv_item, parent, false)
        }

        //image는 glide로 처리해야함.
//        val image = converView?.findViewById<ImageView>(R.id.imageArea)
        val name = converView!!.findViewById<TextView>(R.id.productName)
        val price = converView!!.findViewById<TextView>(R.id.productPrice)

        price!!.text = productList[position].price.toString()
        name!!.text = productList[position].name

        return converView!!
    }
}