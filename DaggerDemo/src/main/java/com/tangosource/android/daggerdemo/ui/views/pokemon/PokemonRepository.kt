package com.tangosource.android.daggerdemo.ui.views.pokemon

import com.tangosource.android.daggerdemo.api.reponses.PokemonSearchResponse
import com.tangosource.android.daggerdemo.api.ApiResponse
import com.tangosource.android.daggerdemo.api.PokemonAPI
import com.tangosource.android.daggerdemo.api.safeAPICall
import com.tangosource.android.daggerdemo.model.PokemonInfo
import com.tangosource.android.daggerdemo.ui.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonAPI: PokemonAPI): BaseRepository() {

    suspend fun getPokemons(limit: Int, offset: Int): ApiResponse<PokemonSearchResponse> = withContext(Dispatchers.IO) {
        return@withContext safeAPICall { pokemonAPI.getPokemons(limit, offset) }
    }

    suspend fun getPokemonById(pokemonId: Int): ApiResponse<PokemonInfo> = withContext(Dispatchers.IO) {
        return@withContext  safeAPICall { pokemonAPI.getPokemon(pokemonId) }
    }

}