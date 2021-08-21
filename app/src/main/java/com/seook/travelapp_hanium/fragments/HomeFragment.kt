package com.seook.travelapp_hanium.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.seook.travelapp_hanium.R
import com.seook.travelapp_hanium.databinding.FragmentHomeBinding
import com.seook.travelapp_hanium.main_search.MainSearchActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.searchBtn.setOnClickListener {
            val intent = Intent(context,MainSearchActivity::class.java )
            startActivity(intent)
        }

        binding.historyTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_historyFragment)
        }
        binding.profileTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        binding.searchTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.travelTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_chooseFragment)
        }

        return binding.root
    }


}