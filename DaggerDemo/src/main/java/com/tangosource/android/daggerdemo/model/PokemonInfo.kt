package com.tangosource.android.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonInfo(
    val id: Int,
    val name: String,
    val order: Int,
    val height: Int,
    val weight: Int,
    val base_experience: Int,
    val abilities: List<PokemonAbilityItem>,
    val sprites: Sprites,
    val types: List<PokemonTypeItem> = listOf()
)