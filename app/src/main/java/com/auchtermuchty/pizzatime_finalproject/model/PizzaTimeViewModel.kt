package com.auchtermuchty.pizzatime_finalproject.model

import androidx.lifecycle.*
import com.auchtermuchty.pizzatime_finalproject.data.Pizza
import com.auchtermuchty.pizzatime_finalproject.data.PizzaDao
import kotlinx.coroutines.launch

class PizzaTimeViewModel(val pizzaDao: PizzaDao) : ViewModel() {
    val allPizzas: LiveData<List<Pizza>> = pizzaDao.getAll().asLiveData()

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price

    private val _size = MutableLiveData<String>()
    val size: LiveData<String> = _size

    private val _toppings = MutableLiveData<MutableList<String>>()
    val toppings: LiveData<MutableList<String>> = _toppings

    init {
        _toppings.value = mutableListOf()
    }
    fun manageTopping(topping: String) {
        if (_toppings.value!!.contains(topping)) {
            _toppings.value!!.remove(topping)
        } else {
            _toppings.value!!.add(topping)
        }
    }
    fun setPrice(price: Double) {
        _price.value = price
    }

    fun setSize(size: Size) {
        _size.value = size.size
    }
    fun addNewPizza() {
        val newPizza = getNewOrderEntry()
        viewModelScope.launch {
            pizzaDao.insert(newPizza)
        }
    }

    private fun getNewOrderEntry(): Pizza {
        return Pizza(
            size = _size.value!!,
            toppings = _toppings.value!!.joinToString(","),
            price = _price.value.toString(),
            delivery = true
        )
    }

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

enum class Size(val size: String) {
    LARGE("Large"),
    MEDIUM("Medium"),
    SMALL("Small")
}