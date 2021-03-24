package com.tangosource.android.daggerdemo.ui.views.pokemon.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tangosource.android.daggerdemo.api.ApiResponse
import com.tangosource.android.daggerdemo.enums.State
import com.tangosource.android.daggerdemo.model.PokemonInfo
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonInfoViewModel  @Inject constructor(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemon = MutableLiveData<PokemonInfo>()
    val pokemon: LiveData<PokemonInfo> = _pokemon

    private val _pokemonImage = MutableLiveData<String>()
    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state
    val pokemonImage: LiveData<String> = _pokemonImage
    private var showFront = true
    var showShinyForm = false
        set(value) {
            field = value
            showFront = !showFront
            loadPokemonImage()
        }

    fun getPokemon(pokemonId: Int) {
        viewModelScope.launch {
            _state.postValue(State.LOADING)
            val response = pokemonRepository.getPokemonById(pokemonId)

            if(response is ApiResponse.Success) {
                _state.postValue(State.SUCCESS)
                _pokemon.postValue(response.data)
            } else {
                _state.postValue(State.ERROR)
            }
        }
    }

    fun loadPokemonImage() {
        if(showFront) {
            _pokemonImage.value = if(showShinyForm) _pokemon.value?.sprites?.frontShiny else _pokemon.value?.sprites?.frontDefault
        } else {
            _pokemonImage.value = if(showShinyForm) _pokemon.value?.sprites?.backShiny else _pokemon.value?.sprites?.backDefault
        }
        showFront = !showFront
    }
}