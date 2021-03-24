package com.tangosource.android.daggerdemo.api.mocks

import com.tangosource.android.daggerdemo.api.reponses.PokemonSearchResponse
import com.tangosource.android.daggerdemo.api.PokemonAPI
import com.tangosource.android.daggerdemo.model.PokemonInfo

class MockPokemonAPI: PokemonAPI {
    override suspend fun getPokemons(limit: Int, offset: Int): PokemonSearchResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(pokemonId: Int): PokemonInfo {
        TODO("Not yet implemented")
    }

}