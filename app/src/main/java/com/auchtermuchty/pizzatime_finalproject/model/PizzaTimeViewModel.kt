package com.auchtermuchty.pizzatime_finalproject.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.auchtermuchty.pizzatime_finalproject.data.Pizza
import com.auchtermuchty.pizzatime_finalproject.data.PizzaDao

class PizzaTimeViewModel(private val pizzaDao: PizzaDao) : ViewModel() {
    fun allPizzas(): List<Pizza> = pizzaDao.getAll()
    fun getPizza(id: Int) : Pizza = pizzaDao.getPizza(id)
}

class PizzaTimeViewModelFactory(
    private val pizzaDao: PizzaDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PizzaTimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PizzaTimeViewModel(pizzaDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}