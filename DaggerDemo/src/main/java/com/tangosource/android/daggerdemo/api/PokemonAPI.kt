package com.tangosource.android.daggerdemo.api

import com.tangosource.android.daggerdemo.api.reponses.PokemonSearchResponse
import com.tangosource.android.daggerdemo.model.PokemonInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @Headers("Accept: application/json")
    @GET("pokemon/")
    @Throws(Exception::class)
    suspend fun getPokemons(@Query("limit") limit: Int = 20, @Query("offset") offset: Int = 20): PokemonSearchResponse

    @Headers("Accept: application/json")
    @GET("pokemon/{pokemonId}")
    @Throws(Exception::class)
    suspend fun getPokemon(@Path("pokemonId") pokemonId: Int): PokemonInfo
}