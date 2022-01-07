package com.android.drinksmaster

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.drinksmaster.data.model.DrinkEntity
import com.android.drinksmaster.domain.TragosDao

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