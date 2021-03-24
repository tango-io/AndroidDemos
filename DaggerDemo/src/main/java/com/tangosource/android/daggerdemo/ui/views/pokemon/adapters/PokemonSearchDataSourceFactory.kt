package com.tangosource.android.daggerdemo.ui.views.pokemon.adapters

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.tangosource.android.daggerdemo.ui.views.pokemon.PokemonRepository
import kotlinx.coroutines.CoroutineScope

class PokemonSearchDataSourceFactory(
    private val pokemonRepository: PokemonRepository,
    private val scope: CoroutineScope
): DataSource.Factory<Int, PokemonSearchItem>() {

    val liveData = MutableLiveData<PokemonSearchDataSource>()
    lateinit var pokemonSearchDataSource: PokemonSearchDataSource

    override fun create(): DataSource<Int, PokemonSearchItem> {
        pokemonSearchDataSource = PokemonSearchDataSource(pokemonRepository, scope)
        liveData.postValue(pokemonSearchDataSource)
        return pokemonSearchDataSource
    }

}