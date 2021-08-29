package com.seook.travelapp_hanium

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ProgressBarBindingAdapter
import com.seook.travelapp_hanium.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {

    val TAG : String ="로그"

    private lateinit var binding : ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail)

        val getName = intent.getStringExtra("name")
        val getPicture = intent.getStringExtra("picture")
        val getPrice = intent.getStringExtra("price")
        val getClicked = intent.getStringExtra("clicked")

        val koreaPrice = Integer.parseInt(getPrice) * 11
        Log.d(TAG, "ProductDetailActivity - onCreate() called ${koreaPrice}")

        binding.productName.text = getName
        binding.koreaPrice.text = koreaPrice.toString()
        binding.otherPrice.text = getPrice.toString()




    }
}