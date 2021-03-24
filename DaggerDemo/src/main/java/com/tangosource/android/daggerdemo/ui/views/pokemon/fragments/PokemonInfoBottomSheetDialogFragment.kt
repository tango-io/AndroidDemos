package com.tangosource.android.daggerdemo.ui.views.pokemon.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tangosource.android.daggerdemo.R
import com.tangosource.android.daggerdemo.model.PokemonInfo
import com.tangosource.android.daggerdemo.enums.PokemonTypes
import com.tangosource.android.daggerdemo.enums.State
import com.tangosource.android.daggerdemo.ui.views.pokemon.view_model.PokemonInfoViewModel
import com.tangosource.android.daggerdemo.utils.visible
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pokemon_info.*
import java.util.*
import javax.inject.Inject

class PokemonInfoBottomSheetDialogFragment(private val pokemonId: Int): BottomSheetDialogFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val pokemonInfoViewModel: PokemonInfoViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pokemonInfoViewModel.pokemon.observe(this, { pokemon ->
            setupProfile(pokemon)
        })

        pokemonInfoViewModel.pokemonImage.observe(this, { pokemonImageUrl ->
            Glide.with(this)
                    .load(pokemonImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .circleCrop()
                    .into(iv_pokemon_image)
        })

        pokemonInfoViewModel.state.observe(this, { state ->
            when (state) {
                State.LOADING -> showLoading()
                State.SUCCESS -> hideLoading()
                State.ERROR -> dismiss()
            }
        })

        pokemonId.let { pokemonInfoViewModel.getPokemon(it) }
    }

    private fun setupProfile(pokemon: PokemonInfo) {
        tv_pokemon_name.text = pokemon.name.capitalize(Locale.getDefault())
        tv_height.text = getString(R.string.height, pokemon.height)
        tv_weight.text = getString(R.string.weight, pokemon.weight)
        tv_pokemon_number.text = getString(R.string.number, pokemon.order)
        if (pokemon.abilities.isNotEmpty()) {
            tv_primary_ability.visible()
            tv_primary_ability.text = getString(R.string.primary_ability, pokemon.abilities[0].ability.name)
            if(pokemon.abilities.size > 1) {
                tv_secondary_ability.visible()
                tv_secondary_ability.text = getString(R.string.secondary_ability, pokemon.abilities[1].ability.name)
                if(pokemon.abilities.size > 2) {
                    tv_hidden_ability.visible()
                    tv_hidden_ability.text = getString(R.string.hidden_ability, pokemon.abilities[2].ability.name)
                }
            }
        }

        if (pokemon.types.isNotEmpty()) {
            val type =
                    PokemonTypes.getPokemonTypeByName(tv_primary_type.context, pokemon.types[0].type.name)
            tv_primary_type.visible()
            tv_primary_type.text = getString(type.nameId)
            tv_primary_type.backgroundTintList =
                    ContextCompat.getColorStateList(tv_primary_type.context, type.colorId)

            if (pokemon.types.size == 2) {
                val secondaryType =
                        PokemonTypes.getPokemonTypeByName(tv_secondary_type.context, pokemon.types[1].type.name)
                tv_secondary_type.visible()
                tv_secondary_type.text = getString(secondaryType.nameId)
                tv_secondary_type.backgroundTintList =
                        ContextCompat.getColorStateList(tv_secondary_type.context, secondaryType.colorId)
            }
        }
        iv_show_shiny.setOnClickListener {
            iv_show_shiny.isActivated = !pokemonInfoViewModel.showShinyForm
            pokemonInfoViewModel.showShinyForm = !pokemonInfoViewModel.showShinyForm
        }
        iv_pokemon_image.setOnClickListener { pokemonInfoViewModel.loadPokemonImage() }
        pokemonInfoViewModel.loadPokemonImage()
    }

    private fun showLoading() {
        // TODO add a way to show a pretty loading animation
    }

    private fun hideLoading() {
        // TODO add a way to hide a pretty loading animation
    }

    companion object {
        const val TAG = "PokemonInfoBottomSheetDialogFragment"
    }
}