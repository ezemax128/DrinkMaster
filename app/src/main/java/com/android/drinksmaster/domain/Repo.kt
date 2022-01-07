package com.android.drinksmaster.domain

import com.android.drinksmaster.vo.Resourse
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.data.model.DrinkEntity

interface Repo {
    suspend fun newCocktailList(tragoName: String): Resourse<List<Drink>>
    suspend fun newListByIngredients(ingredientsName: String): Resourse<List<Drink>>
    suspend fun insertDrink(trago: DrinkEntity)
    suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>>
    suspend fun deleteFavoriteDrink(trago: DrinkEntity)
}