package com.tangosource.android.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonTypeItem(val slot: Int, val type: PokemonType)