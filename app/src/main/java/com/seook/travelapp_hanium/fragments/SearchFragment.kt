package com.seook.travelapp_hanium.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.FragmentSearchBinding
import com.seook.travelapp_hanium.retrofit.ProductModel
import com.seook.travelapp_hanium.retrofit.RetrofitManager
import com.seook.travelapp_hanium.utils.RESPONSE_STATE


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private var items = ArrayList<ProductModel>()
    private lateinit var productLVAdapter: SearchLVAdapter
    val TAG : String ="로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        ProductApi()

        productLVAdapter = SearchLVAdapter(items)
        binding.productList.adapter = productLVAdapter


        binding.historyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_historyFragment)
        }
        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_profileFragment)
        }
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
        binding.travelTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_searchFragment_to_chooseFragment)
        }

        return binding.root
    }

    private fun ProductApi(){
        RetrofitManager.instance.searchProduct(completion = {
            responsestate, responseDataArrayList ->
            when(responsestate){
                RESPONSE_STATE.OKAY->{
                    for(photo in responseDataArrayList){
                        items.add(photo)
                    }
                    productLVAdapter.notifyDataSetChanged()
                }RESPONSE_STATE.FAIL->{
                    Log.d(TAG, "SearchFragment - ProductApi() called/ 호출실패")

                }
            }
        })
    }


}