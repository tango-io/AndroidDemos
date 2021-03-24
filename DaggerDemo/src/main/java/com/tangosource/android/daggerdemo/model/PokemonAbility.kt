package com.tangosource.android.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonAbility(val name: String, val url: String)