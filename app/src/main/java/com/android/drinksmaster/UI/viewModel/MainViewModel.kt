package com.android.drinksmaster.UI.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.vo.Resourse
import com.android.drinksmaster.data.model.DrinkEntity
import com.android.drinksmaster.domain.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo) : ViewModel() {

    private val tragoABuscar = MutableLiveData<String>()
    private val ingredienteABuscar = MutableLiveData<String>()

    fun setTrago(tragoName: String) {
        tragoABuscar.value = tragoName
    }
    fun setIngredient(ingredientName:String){
        ingredienteABuscar.value = ingredientName
    }

    init {
        setTrago("margarita")
        setIngredient("vodka")
    }
    //consigue la lista de tragos que le pedimos por buscador
    val fetchTragos = tragoABuscar.distinctUntilChanged().switchMap { trago->
        liveData(Dispatchers.IO) {
            emit(Resourse.Loading())
            try {
                emit(repo.newCocktailList(trago))
            } catch (e: Exception) {
                emit(Resourse.Failure(e))
            }
        }
    }

    val fetchIngrediente = ingredienteABuscar.distinctUntilChanged().switchMap { ingredient->
        liveData(Dispatchers.IO){
            emit(Resourse.Loading())
            try {
                emit(repo.newListByIngredients(ingredient))
            }catch (e:java.lang.Exception){
                emit(Resourse.Failure(e))
            }
        }
    }
    //-----------------------------
    fun saveDrink(trago: DrinkEntity){
        viewModelScope.launch {
            repo.insertDrink(trago)
        }
    }

    //consigue la lista de tragos favoritos
    fun getAllFavorites() = liveData(Dispatchers.IO){
        emit(Resourse.Loading())
        try {
            emit(repo.getFavoritesDrinks())

        } catch (e: Exception) {
            emit(Resourse.Failure(e))
        }
    }

    fun deleteFavoriteDrink(trago: DrinkEntity){
        viewModelScope.launch {
            repo.deleteFavoriteDrink(trago)
        }
    }
}