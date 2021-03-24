package com.tangosource.android.daggerdemo.api.reponses

import com.tangosource.android.daggerdemo.model.PokemonSearchItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSearchResponse (@Json(name = "results") val results: List<PokemonSearchItem> = listOf())