package com.auchtermuchty.pizzatime_finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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
    ): View {
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
            viewModel.setSize(Size.LARGE)
            createPizzaString()
        }

        binding!!.swtMedium.setOnClickListener{
            binding!!.swtLarge.isClickable = true
            binding!!.swtLarge.isChecked = false
            binding!!.swtSmall.isClickable = true
            binding!!.swtSmall.isChecked = false
            binding!!.swtMedium.isClickable = false
            viewModel.setSize(Size.MEDIUM)
            createPizzaString()
        }

        binding!!.swtSmall.setOnClickListener{
            binding!!.swtLarge.isClickable = true
            binding!!.swtLarge.isChecked = false
            binding!!.swtMedium.isClickable = true
            binding!!.swtMedium.isChecked = false
            binding!!.swtSmall.isClickable = false
            viewModel.setSize(Size.SMALL)
            createPizzaString()
        }

        //The on click listener for the add to order button.  This will do more later
        binding!!.btnAddToOrder.setOnClickListener {
            viewModel.addNewPizza()
            findNavController().navigate(R.id.action_createOrderFragment_to_previousOrdersFragment)
        }

        //finally, sets the default option for pizza size to large
        binding!!.swtLarge.isChecked = true
        binding!!.swtLarge.callOnClick()
    }

    //The method for creating a string for the pizza order as well as setting the price in the UI.
    //Different string resources are used depending on how many toppings are on the pizza with cheese
    //pizza being the default
    fun createPizzaString() {
        val toppings = viewModel.toppings.value
        val size = viewModel.size.value
        when(toppings!!.count()) {
            0 -> binding!!.txtPizza.text = resources.getString(R.string.pizzaNoToppings, size)
            1 -> binding!!.txtPizza.text = resources.getString(R.string.pizzaOneTopping, size, toppings[0])
            else -> {
                val lastTopping = toppings.removeLast()
                val toppingsString = toppings.joinToString()
                binding!!.txtPizza.text = resources.getString(R.string.pizzaManyTopping, size, toppingsString, lastTopping)
            }
        }
        viewModel.calculatePrice()
        binding!!.txtPrice.text = resources.getString(R.string.price_txt, viewModel.price.value.toString())
    }
}