package com.android.drinksmaster.presentation.view

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.android.drinksmaster.R
import com.android.drinksmaster.databinding.FragmentMyBrandBinding


class myBrandFragment : Fragment(R.layout.fragment_my_brand) {
    private lateinit var binding: FragmentMyBrandBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMyBrandBinding.bind(view)
        binding.introLotie.setAnimation(R.raw.calabaza_intro)
        binding.introLotie.playAnimation()
        //go back Controller
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
        }
        timer()
    }

    fun timer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                //Nothing here
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_myBrandFragment_to_listadoCocktail2)
            }

        }.start()
    }


}