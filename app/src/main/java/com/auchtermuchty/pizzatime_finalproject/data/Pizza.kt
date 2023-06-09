package com.auchtermuchty.pizzatime_finalproject.data

import androidx.room.*

@Entity
data class Pizza (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int = 0,
        @ColumnInfo(name = "size")
        val size: String,
        @ColumnInfo(name = "toppings")
        val toppings: String,
        @ColumnInfo(name = "delivery")
        val delivery: Boolean,
        @ColumnInfo(name = "price")
        val price: String
    ) {
        fun getToppingList(): MutableList<String> {
                return toppings.split(",") as MutableList<String>
        }
}