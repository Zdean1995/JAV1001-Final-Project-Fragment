package com.auchtermuchty.pizzatime.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.auchtermuchty.pizzatime_finalproject.CreateOrderFragment
import com.auchtermuchty.pizzatime_finalproject.R
import com.auchtermuchty.pizzatime_finalproject.model.PizzaTimeViewModel

//adapter for the toppings recycler view in the create order fragment
class ToppingsAdapter(var frag: CreateOrderFragment, var viewModel: PizzaTimeViewModel):
    RecyclerView.Adapter<ToppingsAdapter.ToppingsViewHolder>() {
    //the list of all toppings
    var toppingsStrings: MutableList<String> = mutableListOf()

    class ToppingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val swtToppings = view.findViewById(R.id.swt_toppings) as SwitchCompat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToppingsViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.toppings_item, parent, false)

        return ToppingsViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = toppingsStrings.count()


    override fun onBindViewHolder(holder: ToppingsViewHolder, position: Int) {
        holder.swtToppings.text = toppingsStrings[position]
        holder.swtToppings.setOnClickListener {
            viewModel.manageTopping(toppingsStrings[position])
            frag.createPizzaString()
        }
    }
}