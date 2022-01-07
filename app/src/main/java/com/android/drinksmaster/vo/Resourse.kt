package com.android.drinksmaster.vo

import java.lang.Exception

sealed class Resourse<out T>{
    class Loading<out T>: Resourse<T>()
    data class Success<out T>(val data: T): Resourse<T>()
    data class Failure<out T>(val exception: Exception): Resourse<T>()
}
