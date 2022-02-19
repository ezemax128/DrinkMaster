package com.android.drinksmaster.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.drinksmaster.presentation.model.DrinkEntity


//con este codigo declaramos una base de datos de tipo ROOM
@Database(entities = arrayOf(DrinkEntity::class), version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun tragosDao(): TragosDao


    companion object {
        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context): AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "Drinks"
            ).build()
            return INSTANCE!!
        }
    }
}