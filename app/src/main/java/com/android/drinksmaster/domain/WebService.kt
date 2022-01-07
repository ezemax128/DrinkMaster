package com.android.drinksmaster.domain

import com.android.drinksmaster.data.model.DrinksList
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("search.php")
    suspend fun getTragosByName(@Query("s") tragoName: String): DrinksList

    @GET("search.php")
    suspend fun getTragosByIngredient(@Query("i")ingredientName: String): DrinksList
}