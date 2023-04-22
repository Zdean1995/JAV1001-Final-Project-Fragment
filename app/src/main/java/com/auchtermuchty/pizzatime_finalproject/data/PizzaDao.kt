package com.auchtermuchty.pizzatime_finalproject.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {
    @Query("SELECT * FROM pizza ORDER BY id ASC")
    fun getAll(): Flow<List<Pizza>>

    @Query("SELECT * FROM pizza WHERE id = :id")
    fun getPizza(id: Int): Flow<Pizza>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pizza: Pizza)

    @Update
    suspend fun update(pizza: Pizza)

    @Delete
    suspend fun delete(pizza: Pizza)
}