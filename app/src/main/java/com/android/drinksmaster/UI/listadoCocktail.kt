package com.android.drinksmaster.UI

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
import com.android.drinksmaster.AppDataBase
import com.android.drinksmaster.R
import com.android.drinksmaster.UI.MainAdapter.clickedItemRecycler
import com.android.drinksmaster.UI.viewModel.MainViewModel
import com.android.drinksmaster.UI.viewModel.VmFactory
import com.android.drinksmaster.UX.BaseAlert
import com.android.drinksmaster.data.DataSource
import com.android.drinksmaster.data.model.Drink
import com.android.drinksmaster.databinding.FragmentListadoCocktailBinding
import com.android.drinksmaster.domain.RepoImplementer
import com.android.drinksmaster.vo.Resourse


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
                        binding.MainRecycler.adapter = MainAdapter(requireContext(), resultado.data, this)
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
                            requireContext(),
                            result.data, this
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
        bundle.putString("nameTrago", name)
        bundle.putString("descripcionTrago", description)
        bundle.putString("image", image)
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
        findNavController().navigate(R.id.action_listadoCocktail2_to_coktailDetail2, bundle)

    }

    override fun onItemLongClickRecycler(position: Int, drink: Drink) {

    }


}


