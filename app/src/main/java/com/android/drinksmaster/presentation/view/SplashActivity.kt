package com.android.drinksmaster.presentation.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        timer()
    }

    fun timer(){
        object : CountDownTimer(2000, 1000){
            override fun onTick(p0: Long) {
                //nothing here
            }

            override fun onFinish() {
               val intento = Intent(this@SplashActivity, MainActivity::class.java).apply {  }
                startActivity(intento)
            }

        }.start()
    }

    override fun onBackPressed(){}
}