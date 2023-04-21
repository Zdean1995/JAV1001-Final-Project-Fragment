package com.auchtermuchty.pizzatime_finalproject

import android.app.Application
import com.auchtermuchty.pizzatime_finalproject.data.PizzaRoomDatabase

class PizzaTimeApplication: Application() {
    val database: PizzaRoomDatabase by lazy { PizzaRoomDatabase.getDatabase(this)}
}