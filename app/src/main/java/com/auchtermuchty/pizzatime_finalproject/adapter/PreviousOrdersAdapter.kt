package com.auchtermuchty.pizzatime_finalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.auchtermuchty.pizzatime_finalproject.R
import com.auchtermuchty.pizzatime_finalproject.data.Pizza
import com.auchtermuchty.pizzatime_finalproject.databinding.PreviousOrdersItemBinding

class PreviousOrdersAdapter: ListAdapter<Pizza, PreviousOrdersAdapter.PreviousOrdersViewHolder>(DiffCallback) {
    var previousOrders: MutableList<Pizza> = mutableListOf()

    class PreviousOrdersViewHolder(private var binding: PreviousOrdersItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pizza: Pizza) {
            binding.txtOrdersString.text = pizza.toppings
            binding.txtOrdersPrice.text = pizza.price
            if(pizza.delivery){
                binding.txtOrdersDelivery.text = "Delivery"
            } else {
                binding.txtOrdersDelivery.text = "Pick Up"
            }
            binding

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousOrdersViewHolder {
        return PreviousOrdersViewHolder(
            PreviousOrdersItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
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