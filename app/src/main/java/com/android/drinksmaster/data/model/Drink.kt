package com.android.drinksmaster.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

//this class build the data to make the drinks list

data class Drink(
    @SerializedName("idDrink")
    val id: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    //ingredients
    @SerializedName("strIngredient1")
     val ingrediente01: String = "",
    @SerializedName("strIngredient2")
    val ingrediente02: String = "",
    @SerializedName("strIngredient3")
    val ingrediente03: String = "",
    @SerializedName("strIngredient4")
    val ingrediente04: String = "",
    @SerializedName("strIngredient5")
    val ingrediente05: String = "",
    @SerializedName("strIngredient6")
    val ingrediente06: String = "",
    //medidas
    @SerializedName("strMeasure1")
    val medida01: String = "",
    @SerializedName("strMeasure2")
    val medida02: String = "",
    @SerializedName("strMeasure3")
    val medida03: String = "",
    @SerializedName("strMeasure4")
    val medida04: String = "",
    @SerializedName("strMeasure5")
    val medida05: String = "",
    @SerializedName("strMeasure16")
    val medida06: String = "",
)

data class DrinksList(
    @SerializedName("drinks")
    val drinkList: List<Drink>
)

//ROOM IMPLEMENTED
@Entity
data class DrinkEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name="Image_Drink")
    val image: String = "",
    @ColumnInfo(name="Name_Drink")
    val name: String = "",
    @ColumnInfo(name="Instructions")
    val description: String = "",
    @ColumnInfo(name = "strIngredient1")
    val ingrediente01: String = "",
    @ColumnInfo(name = "strIngredient2")
    val ingrediente02: String = "",
    @ColumnInfo(name = "strIngredient3")
    val ingrediente03: String = "",
    @ColumnInfo(name = "strIngredient4")
    val ingrediente04: String = "",
    @ColumnInfo(name = "strIngredient5")
    val ingrediente05: String = "",
    @ColumnInfo(name = "strIngredient6")
    val ingrediente06: String = "",
    //medidas
    @ColumnInfo(name = "strMeasure1")
    val medida01: String = "",
    @ColumnInfo(name = "strMeasure2")
    val medida02: String = "",
    @ColumnInfo(name = "strMeasure3")
    val medida03: String = "",
    @ColumnInfo(name = "strMeasure4")
    val medida04: String = "",
    @ColumnInfo(name = "strMeasure5")
    val medida05: String = "",
    @ColumnInfo(name = "strMeasure16")
    val medida06: String = "",
)