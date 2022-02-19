package com.android.drinksmaster.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.android.drinksmaster.R
import com.android.drinksmaster.presentation.model.Drink

class MainAdapter(
    private val listadoCocktail: List<Drink>,
    private val itemClickRecycler: clickedItemRecycler,
    private val longClickActivated: Boolean,
) :
    RecyclerView.Adapter<MainAdapter.mainViewHolder>() {
    interface clickedItemRecycler {
        fun onItemClickRecycler(Drink: Drink)

        fun onItemLongClickRecycler(position: Int, drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainViewHolder {
        return mainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.drinks_rows, parent, false)
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
                itemClickRecycler.onItemClickRecycler(drink)
            }
            if (longClickActivated) {
                itemx.setOnLongClickListener {
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
}