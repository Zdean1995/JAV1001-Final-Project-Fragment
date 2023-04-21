package com.auchtermuchty.pizzatime_finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.auchtermuchty.pizzatime_finalproject.databinding.FragmentCreateOrderBinding
import com.auchtermuchty.pizzatime_finalproject.databinding.FragmentHomeBinding
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: PizzaTimeViewModel
    var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentbinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding = fragmentbinding
        return fragmentbinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.btnCreateOrder.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_createOrderFragment)
        }
    }
}