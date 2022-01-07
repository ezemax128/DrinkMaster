package com.android.drinksmaster.data

import android.util.Log
import com.android.drinksmaster.vo.Resourse
import com.android.drinksmaster.vo.RetrofitClient
import com.android.drinksmaster.AppDataBase
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.data.model.DrinkEntity


class DataSource(private val appDataBase: AppDataBase) {
    suspend fun getTragosByName(tragoName: String): Resourse<List<Drink>> {
        return Resourse.Success(RetrofitClient.webservice.getTragosByName(tragoName).drinkList)
    }

    suspend fun getTragosByIngredients(ingredientName: String): Resourse<List<Drink>>{
        return Resourse.Success(RetrofitClient.webservice.getTragosByIngredient(ingredientName).drinkList)
    }

    suspend fun saveDrinkIntoRoomDatabase(trago: DrinkEntity) {
        appDataBase.tragosDao().insertFavoriteDrink(trago)
    }

    suspend fun getFavoritesDrinksFromRoom(): Resourse<List<DrinkEntity>> {
        return Resourse.Success(appDataBase.tragosDao().getAllFavoriteDrinks())
    }

    suspend fun deleteDrinkFromRoom(trago: DrinkEntity){
        appDataBase.tragosDao().deleteDrink(trago)

    }
}