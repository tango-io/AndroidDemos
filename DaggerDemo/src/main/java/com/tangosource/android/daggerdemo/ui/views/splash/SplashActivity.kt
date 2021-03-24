package com.tangosource.android.daggerdemo.ui.views.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonSearchActivity
import com.tangosource.android.daggerdemo.R

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, PokemonSearchActivity::class.java))
        finish()
    }
}