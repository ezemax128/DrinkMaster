package com.android.drinksmaster.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.drinksmaster.R
import com.android.drinksmaster.data.model.Drink

class MainAdapter(
    private val context: Context,
    private val listadoCocktail: List<Drink>,
    private val itemClickRecycler: clickedItemRecycler
) :
    RecyclerView.Adapter<MainAdapter.mainViewHolder>() {
    interface clickedItemRecycler {
        fun onItemClickRecycler(
            id: String,
            name: String,
            description: String,
            image: String,
            ingrediente01: String?,
            ingrediente02: String?,
            ingrediente03: String?,
            ingrediente04: String?,
            ingrediente05: String?,
            ingrediente06: String?,
            medida01: String?,
            medida02: String?,
            medida03: String?,
            medida04: String?,
            medida05: String?,
            medida06: String?
        )
        fun onItemLongClickRecycler(position: Int, drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainViewHolder {
        return mainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.drinks_rows, parent, false)
        )
    }

    override fun onBindViewHolder(holder: mainViewHolder, position: Int) {
        holder.bind(listadoCocktail[position], position)
    }

    override fun getItemCount(): Int = listadoCocktail.size


    inner class mainViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val itemx = item

        fun bind(drink: Drink, position: Int) {
            val imagenTrago = itemx.findViewById<ImageView>(R.id.imgCocktail)
            imagenTrago.load(drink.image) {
                transformations(CircleCropTransformation())
            }
            itemx.findViewById<TextView>(R.id.nameDrink).text = drink.name
            itemx.findViewById<TextView>(R.id.descriptionDrink).text = drink.description
            itemx.setOnClickListener {
                itemClickRecycler.onItemClickRecycler(
                    drink.id,
                    drink.name,
                    drink.description,
                    drink.image,
                    drink.ingrediente01,
                    drink.ingrediente02,
                    drink.ingrediente03,
                    drink.ingrediente04,
                    drink.ingrediente05,
                    drink.ingrediente06,
                    drink.medida01,
                    drink.medida02,
                    drink.medida03,
                    drink.medida04,
                    drink.medida05,
                    drink.medida06
                )
            }
            itemx.setOnLongClickListener{
                val drinkToDelete = Drink(
                    drink.id,
                    drink.image,
                    drink.name,
                    drink.description,
                    drink.ingrediente01,
                    drink.ingrediente02,
                    drink.ingrediente03,
                    drink.ingrediente04,
                    drink.ingrediente05,
                    drink.ingrediente06,
                    drink.medida01,
                    drink.medida02,
                    drink.medida03,
                    drink.medida04,
                    drink.medida05,
                    drink.medida06
                )
                itemClickRecycler.onItemLongClickRecycler(position, drinkToDelete)
                return@setOnLongClickListener true
            }

        }
    }
}