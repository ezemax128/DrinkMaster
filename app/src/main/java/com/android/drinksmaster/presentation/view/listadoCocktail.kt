package com.android.drinksmaster.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.drinksmaster.data.local.AppDataBase
import com.android.drinksmaster.R
import com.android.drinksmaster.presentation.view.MainAdapter.clickedItemRecycler
import com.android.drinksmaster.presentation.viewModel.MainViewModel
import com.android.drinksmaster.presentation.viewModel.VmFactory
import com.android.drinksmaster.presentation.UX.BaseAlert
import com.android.drinksmaster.data.DataSource
import com.android.drinksmaster.presentation.model.Drink
import com.android.drinksmaster.databinding.FragmentListadoCocktailBinding
import com.android.drinksmaster.data.repository.RepoImplementer
import com.android.drinksmaster.presentation.model.Resourse


class listadoCocktail() : Fragment(R.layout.fragment_listado_cocktail), clickedItemRecycler {
    private lateinit var binding: FragmentListadoCocktailBinding
    private val viewModel by viewModels<MainViewModel> {
        VmFactory(
            RepoImplementer(
                DataSource(
                    AppDataBase.getDataBase(requireActivity().applicationContext)
                )
            )
        )
    }
    val alerta: BaseAlert = BaseAlert()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListadoCocktailBinding.bind(view)
        val search = binding.searchDrink
        val Check = binding.Chb01
        search.queryHint = "Search by Drink name"
        Check.setOnCheckedChangeListener { button, b ->
            if (button.isChecked) {
                search.queryHint = "Search by Ingredient"
            } else {
                search.queryHint = "Search by Drink name"
            }
        }


        val lottieButon = binding.btnIraFav
        lottieButon.setAnimation(R.raw.favlot)
        lottieButon.playAnimation()
        lottieButon.setOnClickListener {
            findNavController().navigate(R.id.action_listadoCocktail2_to_favoritesfragment)
        }
        //go back Controller
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val vista = layoutInflater.inflate(R.layout.base_warning_alert, null)
            alerta.setupBaseAlert("Do you want to exit now?", requireContext(), vista)


        }
        setupRecyclerView()
        setupViewModel()
        setupSearchView()

    }

    fun setupSearchView() {
        val search = binding.searchDrink
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                    viewModel.setIngredient(p0!!)
                    viewModel.setTrago(p0!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }


    fun setupViewModel() {
        val Check = binding.Chb01
        if (Check.isChecked){
            viewModel.fetchIngrediente.observe(viewLifecycleOwner, Observer { resultado ->
                when (resultado) {
                    is Resourse.Loading -> {
                        binding.loadingProgressBar.visibility = View.VISIBLE
                    }
                    is Resourse.Success -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.MainRecycler.adapter = MainAdapter(resultado.data, this, false)
                    }
                    is Resourse.Failure -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(), "${resultado.exception}",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            })
        }else{
            viewModel.fetchTragos.observe(viewLifecycleOwner, Observer { result ->
                when (result) {
                    is Resourse.Loading -> {
                        binding.loadingProgressBar.visibility = View.VISIBLE
                        binding.loadingLottie.setAnimation(R.raw.loadinglotie)
                    }
                    is Resourse.Success -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        binding.MainRecycler.adapter = MainAdapter(
                            result.data, this, false
                        )
                    }
                    is Resourse.Failure -> {
                        binding.loadingProgressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Error, no es posible cargar los datos ${result.exception}",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }
            })
        }//---

    }



    fun setupRecyclerView() {
        binding.MainRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.MainRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

    }//---------

    override fun onItemClickRecycler(drink: Drink) {
        val bundle = Bundle()
        bundle.putString("id", drink.id)
        bundle.putString("nameTrago", drink.name)
        bundle.putString("descripcionTrago", drink.description)
        bundle.putString("image", drink.image)
        bundle.putString("ing01", drink.ingrediente01)
        bundle.putString("ing02", drink.ingrediente02)
        bundle.putString("ing03", drink.ingrediente03)
        bundle.putString("ing04", drink.ingrediente04)
        bundle.putString("ing05", drink.ingrediente05)
        bundle.putString("ing06", drink.ingrediente06)
        bundle.putString("med01", drink.medida01)
        bundle.putString("med02", drink.medida02)
        bundle.putString("med03", drink.medida03)
        bundle.putString("med04", drink.medida04)
        bundle.putString("med05", drink.medida05)
        bundle.putString("med06", drink.medida06)
        findNavController().navigate(R.id.action_listadoCocktail2_to_coktailDetail2, bundle)

    }

    override fun onItemLongClickRecycler(position: Int, drink: Drink) {
    }


}


