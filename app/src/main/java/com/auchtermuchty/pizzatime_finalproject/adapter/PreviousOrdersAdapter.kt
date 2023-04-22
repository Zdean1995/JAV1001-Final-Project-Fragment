package com.auchtermuchty.pizzatime_finalproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.auchtermuchty.pizzatime_finalproject.R
import com.auchtermuchty.pizzatime_finalproject.data.Pizza
import com.auchtermuchty.pizzatime_finalproject.databinding.PreviousOrdersItemBinding

//adapter for the recycler view in the previous orders fragment
class PreviousOrdersAdapter(val context: Context): ListAdapter<Pizza, PreviousOrdersAdapter.PreviousOrdersViewHolder>(DiffCallback) {

    class PreviousOrdersViewHolder(private var binding: PreviousOrdersItemBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pizza: Pizza) {
            val toppings = pizza.getToppingList()
            when(pizza.toppings.count()) {
                0 -> binding.txtOrdersString.text = context.resources.getString(R.string.pizzaNoToppings, pizza.size)
                1 -> binding.txtOrdersString.text = context.resources.getString(R.string.pizzaOneTopping, pizza.size, toppings[0])
                else -> {
                    val lastTopping = toppings.removeLast()
                    val toppingsString = toppings.joinToString()
                    binding.txtOrdersString.text = context.resources.getString(R.string.pizzaManyTopping, pizza.size, toppingsString, lastTopping)
                }
            }
            binding.txtOrdersPrice.text = context.resources.getString(R.string.price_txt, pizza.price)
            if(pizza.delivery){
                binding.txtOrdersDelivery.text = context.resources.getString(R.string.txt_delivery)
            } else {
                binding.txtOrdersDelivery.text = context.resources.getString(R.string.txt_pick_up)
            }
            binding

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousOrdersViewHolder {
        return PreviousOrdersViewHolder(
            PreviousOrdersItemBinding.inflate(
                LayoutInflater.from(parent.context)
            ), context
        )
    }

    override fun onBindViewHolder(holder: PreviousOrdersViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Pizza>() {
            override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}