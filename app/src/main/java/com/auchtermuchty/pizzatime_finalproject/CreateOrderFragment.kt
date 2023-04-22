package com.auchtermuchty.pizzatime_finalproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auchtermuchty.pizzatime.adapter.ToppingsAdapter
import com.auchtermuchty.pizzatime_finalproject.databinding.FragmentCreateOrderBinding
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModel
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModelFactory
import com.auchtermuchty.pizzatime_finalproject.model.Size

class CreateOrderFragment : Fragment() {
    private var binding: FragmentCreateOrderBinding? = null

    private val viewModel: PizzaTimeViewModel by activityViewModels {
        PizzaTimeViewModelFactory(
            (activity?.application as PizzaTimeApplication).database.pizzaDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentCreateOrderBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding!!.rcyToppings.adapter = ToppingsAdapter(this, viewModel)

        //adding the string array to the recyclerView adapter.  This is done here since I couldn't
        //figure out a way to do it from inside the adapter
        (binding!!.rcyToppings.adapter as ToppingsAdapter).toppingsStrings =
            mutableListOf(*resources.getStringArray(R.array.toppings))


        //on click listeners for each of the size switches.  They all make it so you can't hit themselves again
        //and it unchecks the other two and makes them clickable again.
        binding!!.swtLarge.setOnClickListener{
            binding!!.swtMedium.isClickable = true
            binding!!.swtMedium.isChecked = false
            binding!!.swtSmall.isClickable = true
            binding!!.swtSmall.isChecked = false
            binding!!.swtLarge.isClickable = false
            viewModel.setPrice(10.0)
            viewModel.setSize(Size.LARGE)
            calculatePrice()
        }

        binding!!.swtMedium.setOnClickListener{
            binding!!.swtLarge.isClickable = true
            binding!!.swtLarge.isChecked = false
            binding!!.swtSmall.isClickable = true
            binding!!.swtSmall.isChecked = false
            binding!!.swtMedium.isClickable = false
            viewModel.setPrice(8.0)
            viewModel.setSize(Size.MEDIUM)
            calculatePrice()
        }

        binding!!.swtSmall.setOnClickListener{
            binding!!.swtLarge.isClickable = true
            binding!!.swtLarge.isChecked = false
            binding!!.swtMedium.isClickable = true
            binding!!.swtMedium.isChecked = false
            binding!!.swtSmall.isClickable = false
            viewModel.setPrice(6.0)
            viewModel.setSize(Size.SMALL)
            calculatePrice()
        }

        //The on click listener for the add to order button.  This will do more later
        binding!!.btnAddToOrder.setOnClickListener {
            viewModel.addNewPizza()
        }

        //finally, sets the default option for pizza size to large
        binding!!.swtLarge.isChecked = true
        binding!!.swtLarge.callOnClick()
    }

    fun calculatePrice() {
        val toppings = viewModel.toppings.value
        val size = viewModel.size.value
        when(toppings!!.count()) {
            0 -> binding!!.txtPizza.text = resources.getString(R.string.pizzaNoToppings, size)
            1 -> binding!!.txtPizza.text = resources.getString(R.string.pizzaOneTopping, size, toppings[0])
            else -> {
                val lastTopping = toppings.removeLast()
                val toppings = toppings.joinToString()
                binding!!.txtPizza.text = resources.getString(R.string.pizzaManyTopping, size, toppings, lastTopping)
            }
        }
    }
}