package com.android.drinksmaster.presentation.UX

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.airbnb.lottie.LottieAnimationView
import com.android.drinksmaster.R
import com.google.android.material.internal.ContextUtils.getActivity

class BaseAlert {

    @SuppressLint("RestrictedApi")
    fun setupBaseAlert(txt: String, context: Context, vista: View){
        vista.findViewById<LottieAnimationView>(R.id.img_Alerta).setAnimation(R.raw.exitlotie)
        val alerta = AlertDialog.Builder(context)
        alerta.setView(vista)
        vista.findViewById<TextView>(R.id.txt_dialog01).text = txt
        val dialog = alerta.create()
        alerta.setCancelable(false)
        dialog.show()
        vista.findViewById<Button>(R.id.btncancel).setOnClickListener { dialog.dismiss() }
        //manera correcta de salir de una app desde un fragment:
        vista.findViewById<Button>(R.id.btnExit).setOnClickListener { getActivity(context)!!.finishAffinity()}
    }
}