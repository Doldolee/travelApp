package com.seook.travelapp_hanium.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.FragmentHomeBinding
import com.seook.travelapp_hanium.databinding.FragmentSearchBinding

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
    private lateinit var binding : FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

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


}