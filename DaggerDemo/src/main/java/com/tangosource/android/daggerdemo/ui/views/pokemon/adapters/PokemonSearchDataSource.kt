package com.tangosource.android.daggerdemo.ui.views.pokemon.adapters

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.tangosource.android.daggerdemo.api.ApiResponse
import com.tangosource.android.daggerdemo.enums.State
import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonSearchDataSource(
    private val pokemonRepository: PokemonRepository,
    private val scope:CoroutineScope): PageKeyedDataSource<Int, PokemonSearchItem>() {

    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonSearchItem>
    ) {
        scope.launch {
            updateState(State.LOADING)
            val response = pokemonRepository.getPokemons(20, 0)

            if(response is ApiResponse.Success) {
                updateState(State.SUCCESS)
                val pokemons = response.data.results
                callback.onResult(pokemons, null, pokemons.size)
            } else {
                updateState(State.ERROR)
                loadInitial(params, callback)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonSearchItem>
    ) {
        scope.launch {
            val response = pokemonRepository.getPokemons(20, params.key)
            if (response is ApiResponse.Success) {
                updateState(State.SUCCESS)
                val pokemons = response.data.results
                callback.onResult(pokemons, params.key + pokemons.size)
            } else {
                updateState(State.ERROR)
                loadAfter(params, callback)
            }
        }
    }

    override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PokemonSearchItem>
    ) {

    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}