package com.tangosource.android.daggerdemo.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSearchItem(val name: String, val url: String)
