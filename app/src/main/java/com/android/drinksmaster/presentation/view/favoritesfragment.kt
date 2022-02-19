package com.android.drinksmaster.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.drinksmaster.data.local.AppDataBase
import com.android.drinksmaster.R
import com.android.drinksmaster.presentation.viewModel.MainViewModel
import com.android.drinksmaster.presentation.viewModel.VmFactory
import com.android.drinksmaster.data.DataSource
import com.android.drinksmaster.presentation.model.Drink
import com.android.drinksmaster.presentation.model.DrinkEntity
import com.android.drinksmaster.databinding.FragmentFavoritesfragmentBinding
import com.android.drinksmaster.data.repository.RepoImplementer
import com.android.drinksmaster.presentation.model.Resourse


class favoritesfragment : Fragment(R.layout.fragment_favoritesfragment),
    MainAdapter.clickedItemRecycler {
    private lateinit var binding: FragmentFavoritesfragmentBinding
    private val viewModel by viewModels<MainViewModel> {
        VmFactory(
            RepoImplementer(
                DataSource(
                    AppDataBase.getDataBase(
                        requireActivity().applicationContext
                    )
                )
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesfragmentBinding.bind(view)
        setupRecyclerView()
        setupObserver()
    }

    fun setupObserver() {
        viewModel.getAllFavorites().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resourse.Loading -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                }
                is Resourse.Success -> {
                    binding.loadingProgressBar.visibility = View.GONE
                    val lista: List<Drink> = it.data.map {
                        Drink(
                            it.id,
                            it.image,
                            it.name,
                            it.description,
                            it.ingrediente01,
                            it.ingrediente02,
                            it.ingrediente03,
                            it.ingrediente04,
                            it.ingrediente05,
                            it.ingrediente06,
                            it.medida01,
                            it.medida02,
                            it.medida03,
                            it.medida04,
                            it.medida05,
                            it.medida06
                        )
                    }
                    Log.i("------", it.data.toString())
                    binding.recyclerfavorites.adapter = MainAdapter(lista, this, true)
                }
                is Resourse.Failure -> {
                    binding.loadingProgressBar.visibility = View.GONE
                }
            }
        })
    }

    fun setupRecyclerView() {
        binding.recyclerfavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerfavorites.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onItemClickRecycler(
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
    ) {
        val bundle = Bundle()
        bundle.putString("id", id)
        bundle.putString("image", image)
        bundle.putString("nameTrago", name)
        bundle.putString("descripcionTrago", description)
        bundle.putBoolean("origen", true)
        bundle.putString("ing01", ingrediente01)
        bundle.putString("ing02", ingrediente02)
        bundle.putString("ing03", ingrediente03)
        bundle.putString("ing04", ingrediente04)
        bundle.putString("ing05", ingrediente05)
        bundle.putString("ing06", ingrediente06)
        bundle.putString("med01", medida01)
        bundle.putString("med02", medida02)
        bundle.putString("med03", medida03)
        bundle.putString("med04", medida04)
        bundle.putString("med05", medida05)
        bundle.putString("med06", medida06)
        findNavController().navigate(R.id.action_favoritesfragment_to_coktailDetail2, bundle)
    }



    override fun onItemLongClickRecycler(position: Int, drink: Drink) {
        val entidad = DrinkEntity(
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
        viewModel.deleteFavoriteDrink(entidad)
        val recycler = binding.recyclerfavorites
        recycler.adapter?.notifyItemRemoved(position)
        Toast.makeText(
            requireContext(), " ${drink.name} was removed from favorites",
            Toast.LENGTH_LONG
        ).show()
        setupObserver()

    }


}