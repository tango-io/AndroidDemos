package com.tangosource.android.daggerdemo.ui.views.pokemon.view_model

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tangosource.android.daggerdemo.enums.State
import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonRepository
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.PokemonSearchDataSource
import com.tangosource.android.daggerdemo.ui.views.pokemon.adapters.PokemonSearchDataSourceFactory
import kotlinx.coroutines.*
import javax.inject.Inject

class PokemonSearchViewModel @Inject constructor(pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<PokemonSearchItem>>(listOf())

    private val pokemonSearchDataSourceFactory = PokemonSearchDataSourceFactory(pokemonRepository, viewModelScope)
    val searchState: LiveData<State> = Transformations.switchMap(pokemonSearchDataSourceFactory.liveData, PokemonSearchDataSource::state)

    val pokemonList: LiveData<PagedList<PokemonSearchItem>>

    private val searchScope = CoroutineScope(viewModelScope.coroutineContext + Job())

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        pokemonList = LivePagedListBuilder(pokemonSearchDataSourceFactory, pagedListConfig).build()
    }

    fun getPokemons() = pokemonList

    fun searchPokemonByName(pokemonName: String) {
        searchScope.launch {
            withContext(Dispatchers.IO) {
                _pokemonList.postValue(pokemonList.value?.filter { it.name.contains(pokemonName) })
            }
        }
    }

}