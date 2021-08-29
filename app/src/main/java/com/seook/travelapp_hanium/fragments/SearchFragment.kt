package com.seook.travelapp_hanium.fragments

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.FragmentSearchBinding
import com.seook.travelapp_hanium.retrofit.ProductModel
import com.seook.travelapp_hanium.retrofit.RetrofitManager
import com.seook.travelapp_hanium.utils.App
import com.seook.travelapp_hanium.utils.RESPONSE_STATE
import java.util.*
import kotlin.collections.ArrayList


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
    private lateinit var rvAdapter: ProductSearchRVAdapter

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
        rvAdapter = ProductSearchRVAdapter(requireContext(), items)
        val rv:RecyclerView = binding.rv
        rv.adapter = rvAdapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.searchBtn.setOnClickListener {
            handleSearchButtonUi()
            ProductDetailApi()

        }
        binding.searchTermEditText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG, "SearchFragment - beforeTextChanged() called")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().count() > 0){
                    binding.frameSearchBtn.visibility = View.VISIBLE

                }else{
                    binding.frameSearchBtn.visibility = View.GONE
                }
                if(s.toString().count() ==12){
                    Toast.makeText(requireContext(),"검색어는 12자까지만 입력해라",Toast.LENGTH_LONG).show()
                }
            }
            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "SearchFragment - afterTextChanged() called")

            }

        })



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
            items.clear()
            when(responsestate){
                RESPONSE_STATE.OKAY->{
                    for(photo in responseDataArrayList){
                        items.add(photo)
                    }
                    rvAdapter.notifyDataSetChanged()
                }RESPONSE_STATE.FAIL->{
                    Log.d(TAG, "SearchFragment - ProductApi() called/ 호출실패")

                }
            }
        })
    }

    private fun handleSearchButtonUi(){
        binding.btnProgress.visibility = View.VISIBLE
        binding.searchBtn.text =""
        Handler().postDelayed({
            binding.btnProgress.visibility = View.INVISIBLE
            binding.searchBtn.text = "검색"
        }, 1500)
    }

    private fun ProductDetailApi(){
        RetrofitManager.instance.searchProductDetail(searchTerm = binding.searchTermEditText.text.toString(),completion = {
                responsestate, responseDataArrayList ->
            items.clear()
            when(responsestate){
                RESPONSE_STATE.OKAY->{
                    Log.d(TAG, "SearchFragment - ProductDetailApi() called//api호출완료")
                    Log.d(TAG, "SearchFragment - ProductDetailApi() called 이건 item 갱신 전 ${items}")
                    for(ProductDetail in responseDataArrayList){
                        items.add(ProductDetail)
                    }
                    rvAdapter.notifyDataSetChanged()
                    Log.d(TAG, "SearchFragment - ProductDetailApi() called 이건 버튼 클릭 동작 ${items}")

                }
                RESPONSE_STATE.FAIL->{
                    Log.d(TAG, "SearchFragment - ProductDetailApi called/ 호출실패")

                }
            }
        })
    }


}