package com.tangosource.android.daggerdemo.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonAbilityItem(
    val ability: PokemonAbility,
    @Json(name = "is_hidden") val isHidden: Boolean,
    val slot: Int
)