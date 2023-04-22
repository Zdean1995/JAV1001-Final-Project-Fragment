package com.auchtermuchty.pizzatime_finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.auchtermuchty.pizzatime_finalproject.databinding.FragmentHomeBinding

//fragment used as a home screen
class HomeFragment : Fragment() {
    var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.btnCreateOrder.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createOrderFragment)
        }

        binding!!.btnPreviousOrders.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_previousOrdersFragment)

        }
    }
}