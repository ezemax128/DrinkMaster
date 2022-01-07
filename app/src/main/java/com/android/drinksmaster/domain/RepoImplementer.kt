package com.android.drinksmaster.domain

import android.util.Log
import com.android.drinksmaster.vo.Resourse
import com.android.drinksmaster.data.DataSource
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.data.model.DrinkEntity

class RepoImplementer(private val dataSource: DataSource) : Repo {
    override suspend fun newCocktailList(tragoName: String): Resourse<List<Drink>> {
        return dataSource.getTragosByName(tragoName)
    }

    override suspend fun newListByIngredients(ingredientsName: String): Resourse<List<Drink>> {
        return dataSource.getTragosByIngredients(ingredientsName)
    }

    override suspend fun insertDrink(trago: DrinkEntity) {
        dataSource.saveDrinkIntoRoomDatabase(trago)
    }

    override suspend fun getFavoritesDrinks(): Resourse<List<DrinkEntity>> {
        return dataSource.getFavoritesDrinksFromRoom()
    }

    override suspend fun deleteFavoriteDrink(trago: DrinkEntity) {
        dataSource.deleteDrinkFromRoom(trago)
    }
}