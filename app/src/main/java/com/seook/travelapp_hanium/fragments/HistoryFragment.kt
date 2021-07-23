package com.seook.travelapp_hanium.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.FragmentChooseBinding
import com.seook.travelapp_hanium.databinding.FragmentHistoryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_homeFragment)
        }
        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_profileFragment)
        }
        binding.searchTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_searchFragment)
        }
        binding.travelTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_historyFragment_to_chooseFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}