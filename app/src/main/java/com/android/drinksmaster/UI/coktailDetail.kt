package com.android.drinksmaster.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.android.drinksmaster.AppDataBase
import com.android.drinksmaster.R
import com.android.drinksmaster.UI.viewModel.MainViewModel
import com.android.drinksmaster.UI.viewModel.VmFactory
import com.android.drinksmaster.data.DataSource
import com.android.drinksmaster.data.model.DrinkEntity
import com.android.drinksmaster.databinding.FragmentCoktailDetailBinding
import com.android.drinksmaster.domain.RepoImplementer


class coktailDetail : Fragment(R.layout.fragment_coktail_detail) {
    private lateinit var binding: FragmentCoktailDetailBinding
    private lateinit var id: String
    private lateinit var nameTrago: String
    private lateinit var descricionTrago: String
    private lateinit var imagenTrago: String
    private lateinit var ing01: String
    private lateinit var ing02: String
    private lateinit var ing03: String
    private lateinit var ing04: String
    private lateinit var ing05: String
    private lateinit var ing06: String
    private lateinit var med01: String
    private lateinit var med02: String
    private lateinit var med03: String
    private lateinit var med04: String
    private lateinit var med05: String
    private lateinit var med06: String
    private var origen = false
    private val viewModel by viewModels<MainViewModel> {
        VmFactory(
            RepoImplementer(DataSource(
                AppDataBase.getDataBase(requireActivity().
        applicationContext)))
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCoktailDetailBinding.bind(view)

        requireArguments().let { bundle ->
            id = bundle.getString("id").toString()
            nameTrago = bundle.getString("nameTrago").toString()
            descricionTrago = bundle.getString("descripcionTrago").toString()
            imagenTrago = bundle.getString("image").toString()
            ing01 = bundle.getString("ing01").toString()
            ing02 = bundle.getString("ing02").toString()
            ing03 = bundle.getString("ing03").toString()
            ing04 = bundle.getString("ing04").toString()
            ing05 = bundle.getString("ing05").toString()
            ing06 = bundle.getString("ing06").toString()
            med01 = bundle.getString("med01").toString()
            med02 = bundle.getString("med02").toString()
            med03 = bundle.getString("med03").toString()
            med04 = bundle.getString("med04").toString()
            med05 = bundle.getString("med05").toString()
            med06 = bundle.getString("med06").toString()

            origen = bundle.getBoolean("origen")
        }
        //set view with data from bundle:
        binding.TragoName.text = nameTrago
        binding.descritionTrago.text = descricionTrago
        binding.imagenTrago.load(imagenTrago)
        if (ing01=="null") binding.ing01.isVisible = false
        if (med01=="null") binding.med01.isVisible = false
        if (ing02=="null") binding.ing02.isVisible = false
        if (med02=="null") binding.med02.isVisible = false
        if (ing03=="null") binding.ing03.isVisible = false
        if (med03=="null") binding.med03.isVisible = false
        if (ing04=="null") binding.ing04.isVisible = false
        if (med04=="null") binding.med04.isVisible = false
        if (ing05=="null") binding.ing05.isVisible = false
        if (med05=="null") binding.med05.isVisible = false
        if (ing06=="null") binding.ing06.isVisible = false
        if (med06=="null") binding.med06.isVisible = false
        binding.ing01.text = ing01
        binding.ing02.text = ing02
        binding.ing03.text = ing03
        binding.ing04.text = ing04
        binding.ing05.text = ing05
        binding.ing06.text = ing06
        binding.med01.text = med01
        binding.med02.text = med02
        binding.med03.text = med03
        binding.med04.text = med04
        binding.med05.text = med05
        binding.med06.text = med06
        //--------------------------------
        val btnfavorite = binding.btnFavorite
        btnfavorite.isVisible = !origen
        btnfavorite.setAnimation(R.raw.starfavorite)
        var valor = false
        btnfavorite.setOnClickListener {
            valor = true
            when (valor) {
                 true -> {btnfavorite.playAnimation()
                     btnfavorite.isClickable = false
                    viewModel.saveDrink(DrinkEntity(
                        id,
                        imagenTrago,
                        nameTrago,
                        descricionTrago,
                        ing01,
                        ing02,
                        ing03,
                        ing04,
                        ing05,
                        ing06,
                        med01,
                        med02,
                        med03,
                        med04,
                        med05,
                        med06
                    ))
                    Toast.makeText(
                        requireContext(),
                        "$nameTrago was saved into favorites", Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

    }


}