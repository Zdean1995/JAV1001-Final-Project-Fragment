package com.auchtermuchty.pizzatime_finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModel
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModelFactory

class ConfirmOrderFragment : Fragment() {
    private val viewModel: PizzaTimeViewModel by activityViewModels {
        PizzaTimeViewModelFactory(
            (activity?.application as PizzaTimeApplication).database.pizzaDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_order, container, false)
    }
}