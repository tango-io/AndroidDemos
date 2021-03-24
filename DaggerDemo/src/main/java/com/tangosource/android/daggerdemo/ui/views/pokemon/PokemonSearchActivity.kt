package com.tangosource.android.daggerdemo.ui.views.pokemon

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.PokemonItemClick
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.PokemonSearchItemPagedListAdapter
import com.tangosource.android.daggerdemo.ui.views.pokemon.view_model.PokemonSearchViewModel
import com.tangosource.android.daggerdemo.R
import com.tangosource.android.daggerdemo.ui.base.BaseActivity
import com.tangosource.android.daggerdemo.ui.base.SpacesItemDecoration
import com.tangosource.android.daggerdemo.ui.views.pokemon.fragments.PokemonInfoBottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_pokemon_search.*
import javax.inject.Inject

class PokemonSearchActivity : BaseActivity(R.layout.activity_pokemon_search) {

    @Inject
    lateinit var pokemonViewModel: PokemonSearchViewModel

    private val pokemonClick: PokemonItemClick = { index, _ ->
        val dialog = PokemonInfoBottomSheetDialogFragment(index + 1)
        dialog.show(supportFragmentManager, PokemonInfoBottomSheetDialogFragment.TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv_pokemons.apply {
            addItemDecoration(SpacesItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_4)))
            setHasFixedSize(true)
            adapter = PokemonSearchItemPagedListAdapter(pokemonClick)
        }

        pokemonViewModel.pokemonList.observe(this, {
            (rv_pokemons.adapter as PokemonSearchItemPagedListAdapter).submitList(it)
        })

        pokemonViewModel.searchState.observe(this, {
            //TODO add a loader when the list is loading more items
        })

        sv_pokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                pokemonViewModel.searchPokemonByName(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                pokemonViewModel.searchPokemonByName(query)
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        pokemonViewModel.getPokemons()
    }
}