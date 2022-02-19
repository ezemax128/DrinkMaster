package com.android.drinksmaster.data.repository

import com.android.drinksmaster.presentation.model.Resourse
import com.android.drinksmaster.presentation.model.Drink
import com.android.drinksmaster.presentation.model.DrinkEntity

interface Repo {
    suspend fun newCocktailList(tragoName: String): Resourse<List<Drink>>
    suspend fun newListByIngredients(ingredientsName: String): Resourse<List<Drink>>
    suspend fun insertDrink(trago: DrinkEntity)
    suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>>
    suspend fun deleteFavoriteDrink(trago: DrinkEntity)
}