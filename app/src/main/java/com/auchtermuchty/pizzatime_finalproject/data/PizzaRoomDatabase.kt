package com.auchtermuchty.pizzatime_finalproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pizza::class], version = 2, exportSchema = false)
abstract class PizzaRoomDatabase : RoomDatabase() {
    abstract fun pizzaDao(): PizzaDao

    companion object {
        @Volatile
        private var INSTANCE: PizzaRoomDatabase? = null

        fun getDatabase(context: Context): PizzaRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PizzaRoomDatabase::class.java,
                    "pizza_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}