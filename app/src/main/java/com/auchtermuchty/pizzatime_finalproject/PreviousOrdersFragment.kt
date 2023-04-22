package com.auchtermuchty.pizzatime_finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.auchtermuchty.pizzatime_finalproject.adapter.PreviousOrdersAdapter
import com.auchtermuchty.pizzatime_finalproject.databinding.FragmentPreviousOrdersBinding
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModel
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModelFactory

class PreviousOrdersFragment : Fragment() {
    private var binding: FragmentPreviousOrdersBinding? = null
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
        val fragmentBinding = FragmentPreviousOrdersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PreviousOrdersAdapter()
        binding!!.rcyOrders.adapter = PreviousOrdersAdapter()
        binding!!.rcyOrders.layoutManager = LinearLayoutManager(this.context)
        binding!!.rcyOrders.adapter = adapter

        viewModel.allPizzas.observe(this.viewLifecycleOwner) { pizzas ->
            pizzas.let {
                adapter.submitList(it)
            }
        }
    }
}