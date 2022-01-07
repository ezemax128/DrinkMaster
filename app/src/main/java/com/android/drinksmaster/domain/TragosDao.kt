package com.android.drinksmaster.domain

import androidx.room.*
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.data.model.DrinkEntity
@Dao
interface TragosDao {

    @Query("SELECT * FROM DrinkEntity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteDrink(trago: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)
}